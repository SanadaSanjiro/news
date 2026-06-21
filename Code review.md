---
source: https://github.com/SanadaSanjiro/news
date: 2026-06-21
---
# Организация проекта
1. Написать тесты
2. Сгруппировать dto по пакетам соответственно домену
3. Выделить specification в отдельный пакет, напрмер `com.springbox.news.repository.specification`
4. Добавить к названиям сущностей суффикс `Entity`, например `User` -> `UserEntity`
5. Вместо `TestData` рекомендую освоить хотя бы flyway или лучше сразу liquibase
# Общие рекомендации
## Добавить интерфейсы для контроллеров
В интерфейсы будут вынесены большинство аннотаций и документация
Было:
```java
@RestController  
@RequestMapping("/api/v1/user")  
@RequiredArgsConstructor  
@Validated  
public class UserController {  
  
    private final UserService userService;  
  
    private final UserMapperV1 userMapper;  
  
    @GetMapping  
    public ResponseEntity<UserListResponse> findAll(@Valid UserFilter filter) {  
        return ResponseEntity.ok(  
                userMapper.userListToUserListResponse(  
                        userService.findAll(filter).toList()  
                )  
        );  
    }
}
```
Стало:
```java
@Validated  
@RequestMapping("/api/v1/user")  
public interface UserApi {  
    @GetMapping  
    ResponseEntity<UserListResponse> findAll(@Valid UserFilter filter);
}

@RestController  
@RequiredArgsConstructor  
public class UserController implements UserApi {  
  
    private final UserService userService;  
  
    private final UserMapperV1 userMapper;  
  
    @Override  
    public ResponseEntity<UserListResponse> findAll(UserFilter filter) {  
        return ResponseEntity.ok(  
                userMapper.userListToUserListResponse(  
                        userService.findAll(filter).toList()  
                )  
        );  
    }
}
```
## Добавить логирование
```java
@Slf4j  // предоставляется Lombok
@RestController  
@RequiredArgsConstructor  
public class UserController implements UserApi {  
  
    private final UserService userService;  
  
    private final UserMapperV1 userMapper;  
  
    @Override  
    public ResponseEntity<UserListResponse> findAll(UserFilter filter) {  
        log.info("Запрос списка пользователей");  
		var response = userMapper.userListToUserListResponse(  
		        userService.findAll(filter).toList()  
		);  
		log.debug("Срез списка пользователей: limit={}, offset={}",  
		        filter.getPageSize(), filter.getPageNumber());  
		return ResponseEntity.ok(response);
    }
}
```
## Перейти на Spring Security
Валидация пользователей через AOP — интересное решение, реализовано довольно качественно и органично, но лучше использовать JWT и зашивать эти права в claims, будет ближе к реальной среде.
## Дополнительная нагрузка на каждый update
```java
	// много дублирующегося кода: сначала 2 запроса в БД в маппере, потом ещё раз те же самые в сервисе
	// возможно, всё это можно организовать чисто средствами hibernate
	// так код станет проще, трафика на БД меньше, приложение быстрее
	    
	public Comment requestToComment(UpsertCommentRequest request) {  
	    Comment comment = new Comment();  
	    comment.setText(request.getText());  
	    comment.setCommentAuthor(userService.findById(request.getUserId()));  
	    comment.setNews(newsService.findById(request.getNewsId()));  
	    return comment;  
	}

    public Comment update(Comment comment) {  
        Comment existedComment = findById(comment.getId());  
//        User existedUser = getUser(comment.getCommentAuthor().getId());  
//        News existedNews = getNews(comment.getNews().getId());  
        BeanUtils.copyNonNullProperties(comment, existedComment);  
//        comment.setCommentAuthor(existedUser);  
//        comment.setNews(existedNews);  
        return repository.save(existedComment);  
    }
```
## Использование reflection
BeanUtils#copyNonNullProperties использует Java Reflection API — плохая практика применять эти инструменты в коде с бизнес-логикой. Использование reflection должно быть полностью контролируемым, т.к. можно получить ошибки в runtime, которые тяжело отловить. Использование reflection допускается в тестовой среде, например, для инициализации private полей к которым нет прямого доступа.
 *В конкретно вашем случае рекомендую делать дополнительные мапперы на каждую сущность* 
# Архитектурные моменты
1. С точки зрения SOLID и MVC, не совсем корректно держать и вызывать маппер в контроллере, этим должен заниматься сервисный слой
2. Для именования REST-endpoint'ов лучше использовать множественное число (userS, categorIES и т.д.)
3. Параметры пагинации (limit & offset, они же pageSize & pageNumber) чаще используют как `@RequestParam` со значениями по умолчанию
	- `UserFilter` & `NewsCategoryFilter` в таком случае вообще не понадобятся
4. Ничего страшного в том, чтобы возвращать из контроллера объект `Page<>`
# Прочие замечания
Опечатка
```java
@PostMapping  
@Validated({Marker.OnUpdate.class, Default.class})  //Думаю, тут должно быть Marker.OnCreate
public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request) {  
    News news = newsService.save(newsMapper.requestToNews(request));  
  
    return ResponseEntity.status(HttpStatus.CREATED)  
            .body(newsMapper.newsToResponse(news));  
}
```
---
Возможно получить проблему N+1 из-за `FetchType.LAZY`
```java
@OneToMany(fetch = FetchType.LAZY, mappedBy = "newsAuthor", cascade = CascadeType.ALL)  
@ToString.Exclude  
List<News> newsList = new ArrayList<>();  
  
@OneToMany(fetch = FetchType.LAZY, mappedBy = "commentAuthor", cascade = CascadeType.ALL)  
@ToString.Exclude  
List<Comment> comments = new ArrayList<>();
```
---
`JpaSpecificationExecutor` используется только для `NewsRepository`, нет необходимости утяжелять остальные

---
Более современный подход - использовать String#formatted:
```java
public NewsCategory findById(long id) {  
	return repository.findById(id).orElseThrow(()->  
//                new EntityNotFoundException(MessageFormat  
//                        .format("No news category with id = {0} found", id)));  
			new EntityNotFoundException("No news category with id = %d found".formatted(id)));  
}
```
---
Нет единого кодстайла
```java
public ResponseEntity<CommentListResponse> findByNewsId(  
        @RequestParam(value="newsid") @Min(0) long newsID) {  
    return ResponseEntity.ok(  
            commentMapper.commentListToCommentListResponse(commentService.findByNewsId(newsID))  
    );  
}
```
```java
public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request) {  
    Comment newComment = commentService.save(commentMapper.requestToComment(request));  
  
    return ResponseEntity.status(HttpStatus.CREATED).body(  
            commentMapper.commentToResponse(newComment)  
    );  
}
```
# Плюсы
Благодарен как ревьювер за:
- docker-compose
- postman.collection

Плюсы для вас как для разработчика:
- Опыт использования Spring AOP
- Опыт использования mapstruct
- Реализована пагинация
- Хороший подход к обработке исключений, в дальнейшем рекомендую реализовать фабрику исключений
# Что почитать
- [REST API Best Practices / Хабр](https://habr.com/ru/articles/351890/)
- [Переписывая историю: от инструментов версионирования БД к практике / Хабр](https://habr.com/ru/companies/spring_aio/articles/827976/)