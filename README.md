# Design Patterns in Kotlin

## Overview
Design patterns are reusable solutions to common problems in software design. They provide tested, proven development paradigms that can speed up the development process by providing standardized approaches to software development challenges.

This project demonstrates the implementation of fundamental design patterns using Kotlin, complete with unit tests and practical examples.

## Design Patterns Implemented

### 1. Observer Pattern

#### Purpose
The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

#### Implementation Details
Our implementation showcases a university newsletter system where:
- `UniversityNewsletter` acts as the Subject (Observable)
- `NewsletterDisplayListener` acts as the Observer
- Multiple displays can subscribe to receive course updates

#### Key Components
- `Newsletter<T>`: Interface defining subscription methods
- `NewsletterListener`: Interface for observers
- `Display`: Interface for displaying updates
- `UniversityNewsletter`: Concrete subject implementation
- `NewsletterDisplayListener`: Concrete observer implementation

#### Usage Example
```kotlin
// Create the newsletter (Subject)
val newsletter = UniversityNewsletter()

// Create displays (Observers)
val mobileDisplay = NewsletterDisplayListener("Mobile", newsletter = newsletter)
val webDisplay = NewsletterDisplayListener("Web", newsletter = newsletter)

// Subscribe displays
newsletter.subscribe(mobileDisplay)
newsletter.subscribe(webDisplay)

// Update course information
newsletter.updateData("Kotlin Programming", 30)
```

### 2. Singleton Pattern

#### Purpose
The Singleton pattern ensures a class has only one instance and provides a global point of access to that instance.

#### Implementation Details
Our implementation demonstrates a course management system where:
- Single instance maintains course information
- Thread-safe instance creation (using Kotlin's `object` declaration)
- Automatic initialization

#### Key Components
- `Singleton`: Main singleton object
- `MyClass`: Example class demonstrating singleton usage

#### Usage Example
```kotlin
// Access the singleton instance
Singleton.courseName = "Programming 101"
Singleton.printName()

// Create class that uses singleton
val myClass = MyClass()
```

## Project Structure
```
src/
├── main/
│   └── kotlin/
│       └── design/
│           └── patterns/
│               ├── observer/
│               │   ├── Display.kt
│               │   ├── Newsletter.kt
│               │   ├── NewsletterListener.kt
│               │   ├── NewsletterDisplayListener.kt
│               │   └── UniversityNewsletter.kt
│               └── singleton/
│                   ├── Singleton.kt
│                   └── MyClass.kt
└── test/
    └── kotlin/
        └── design/
            └── patterns/
                ├── observer/
                │   └── NewsletterDisplayListenerTest.kt
                └── singleton/
                    └── SingletonTest.kt
```

## Testing
The project includes comprehensive unit tests for both design patterns:

### Observer Pattern Tests
- Multiple observer notification
- Unsubscription handling
- Cross-platform output verification
- State management validation

### Singleton Pattern Tests
- Single instance verification
- State consistency
- Thread safety
- Cross-platform compatibility

## Best Practices Demonstrated

### Observer Pattern
1. Loose coupling between subject and observers
2. Support for multiple observers
3. Clean unsubscription handling
4. Thread-safe implementation

### Singleton Pattern
1. Thread-safe instance creation
2. Lazy initialization
3. Immutable state where possible
4. Clear access points

## Common Pitfalls to Avoid

### Observer Pattern
- Memory leaks from forgotten subscriptions
- Circular update loops
- Thread safety issues in notification
- Heavy update operations

### Singleton Pattern
- Overuse of global state
- Testing difficulties
- Hidden dependencies
- Concurrency issues

## When to Use

### Observer Pattern
- Event handling systems
- User interface updates
- Real-time data monitoring
- Publish-subscribe systems

### Singleton Pattern
- Configuration management
- Connection pools
- Logging services
- Cache implementations

## Contributing
Feel free to contribute to this project by:
1. Creating issues for bugs or suggestions
2. Submitting pull requests with improvements
3. Adding documentation or examples
4. Adding new design pattern implementations

## License
Academic and Educational License

## Authors
Maikol Guzman (mikeguzman.dev)

## Acknowledgments
- Design Patterns: Elements of Reusable Object-Oriented Software
- Kotlin Programming Language Documentation
- JUnit Testing Framework