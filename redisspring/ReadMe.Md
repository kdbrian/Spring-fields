## Project aimed at exploring capabilities of redis wit a backend app.

> Note <br>
> I selected spring but it should be stack independent
> 

### A Todos app

- Functional requirements
  1. User must be able to create account 
  2. User must be able to load account details.
  3. User must be able to add & update todos 
  4. Users must be able to delete todos
  5. User can delete account
  
- Non Functional requirements. 
  1. Frontend should allow locally updates with sync
  2. Display metrics in frontend.

### What we know
```json
{
  "title": "Todo title",
  "updatedAt": "timestamp",
  "isComplete": "false"
}
```

### Todo service should
```kotlin
    fun addTodo(dto : TodoDto) : Todo
    fun deleteTodo(id : Int) : Boolean
    fun todoById(id : Int) : Todo?
    fun updateTodo(id : Int, dto : TodoDto) : Boolean
```

## Stack
- Spring web (Rest Api+ graphQl)
- Redis Database.
- Actuator - App health
- Portable - Containerize