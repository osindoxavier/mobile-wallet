# Mobile Wallet App

Mobile Wallet App is an Android app designed for easy and secure money transfers, allowing users to login, view recent transactions, see full transaction history, check account balance, view and edit their profile, and log out. The app is built with the latest Android tech stack, following best practices for a modern, adaptable, and user-friendly experience.

## Key Features

- **Built with Kotlin & Jetpack Compose**: 100% Kotlin codebase with Jetpack Compose UI components. No XML layouts or Java code.
- **Material 3**: Utilizes the Material 3 library to ensure a modern and visually appealing interface.
- **Material3 Adaptive Library**: Allows for responsive UI that adapts dynamically across screen sizes, supporting small phones, foldable, and tablets.
- **Single Activity Architecture**: Built around a single-activity pattern to optimize navigation and simplify state management.
- **MVVM with Clean Architecture**: Structured following MVVM (Model-View-ViewModel) and Clean Architecture principles, promoting separation of concerns and testability.

### Functionalities
1. **Login**: Login into account to access the account.
2. **Send Money**: Quickly transfer funds to other users securely.
3. **View Last 5 Transactions**: Easy access to recent transactions.
4. **Full Transaction History**: View all transactions.
5. **Profile Section**: View and edit user details.
6. **Account Balance**: Check the current balance at any time.
7. **Logout**: Securely log out of the app.

### Architecture & Libraries

- **Kotlin (JDK 17)**: Project uses Kotlin exclusively, leveraging JDK 17.
- **Jetpack Compose**: Entirely Compose-based UI, with no usage of Fragments.
- **Material 3**: Incorporates Material Design 3 and its adaptive components.
- **MVVM and Clean Architecture**: Organized to maintain separation of concerns, with repositories, use cases, and ViewModels driving the app’s layers.
- **Navigation Suite**: Uses Material3 adaptive navigation suite and `androidx.navigation.NavHost` for efficient routing and navigation.
- **Gradle Version Catalog**: Manages dependencies using Gradle’s version catalog for streamlined configuration.
- **Room Database**: Offline support using Room to store transaction data and other information locally.
- **Dependency Injection**: Dagger and Hilt for dependency management.
- **GitHub Actions**: Automated checks run on every push and pull request to ensure code quality and functional integrity.

### App Structure

- **Splash Screen**: A visually appealing splash screen with an edge-to-edge layout.
- **Compose Material 3 Theme**: Consistent theming across the app, using custom Compose Material 3 themes.
- **Navigation**: Efficient single-activity pattern using `NavHost` and adaptive navigation.

### Testing

- **Unit Tests**: Comprehensive unit tests for core functionalities.
- **Integration Tests**: Tests to validate end-to-end functionality, focusing on UI and data layer integration.
