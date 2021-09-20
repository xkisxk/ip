# User Guide
Duke is a desktop app that can create and list different types of tasks via a Command Line Interface (CLI).

## Features

### Add todo task 
**Format:** `todo <description of the task>`

Adds a todo task and prints an added message if successful. Prints an error message containing what is wrong if unsuccessful.

Example of usage: 

`todo homework`

Expected outcome:

```
Got it. I have added this task:
   [T][ ] homework
Now you have 1 items.
```

### Add deadline task
**Format:** `deadline <description of the task> /by <date>`

Adds a deadline task and prints an added message if successful. Prints an error message containing what is wrong if unsuccessful.
Date must be in the form of YYYY-MM-DD.

Example of usage:

`deadline CS2113T Assignment /by 2021-09-19`

Expected outcome:

```
Got it. I have added this task:
   [D][ ] CS2113T Assignment (by: Sep 19 2021)
Now you have 2 items.
```

### Add event task
**Format:** `event <description of the task> /at <date>`

Adds an event task and prints an added message if successful. Prints an error message containing what is wrong if unsuccessful.
Date must be in the form of YYYY-MM-DD.

Example of usage:

`event CS2113T Lecture /at 2021-09-17`

Expected outcome:

```
Got it. I have added this task:
   [E][ ] CS2113T Lecture (at: Sep 17 2021)
Now you have 3 items.
```

### List all tasks
**Format:** `list`

Lists the entire task list in the order that the tasks were added in. Prints a numbered list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your to do list:
1.[T][ ] homework
2.[D][ ] CS2113T Assignment (by: Sep 19 2021)
3.[E][X] CS2113T Lecture (at: Sep 17 2021)
4.[D][ ] CS2113T Quiz (by: Oct 1 2021)
5.[T][ ] CS2113T Lecture Video
6.[D][ ] CS2113T iP (by: Oct 1 2021)
```

### Find task
**Format:** `find <description>`

Finds all tasks that contains the description or date and prints them in a numbered list. 

Example of usage:

`find CS2113T`

Given the list:

```
Here are the tasks in your to do list:
1.[T][ ] homework
2.[D][ ] CS2113T Assignment (by: Sep 19 2021)
3.[E][X] CS2113T Lecture (at: Sep 17 2021)
4.[D][ ] CS2113T Quiz (by: Oct 1 2021)
5.[T][ ] CS2113T Lecture Video
6.[D][ ] CS2113T iP (by: Oct 1 2021)
```

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][ ] CS2113T Assignment (by: Sep 19 2021)
2.[E][X] CS2113T Lecture (at: Sep 17 2021)
3.[D][ ] CS2113T Quiz (by: Oct 1 2021)
4.[T][ ] CS2113T Lecture Video
5.[D][ ] CS2113T iP (by: Oct 1 2021)
```

### Delete task
**Format:** `delete <index>`

Deletes the task at the given index and prints a task deleted message. Prints an error message if index is out of bounds.

Example of usage:

`delete 4`

Expected outcome:

```
Avoiding doing this task?! Just kidding.
I've deleted this task:
   [D][ ] CS2113T Quiz (by: Oct 1 2021)
Now you have 5 items.
```

### Exit
**Format:** `bye`

Exits the program. Prints a goodbye message.

Example of usage:

`bye`

Expected outcome:

```
Bye. Talk to you later!
```