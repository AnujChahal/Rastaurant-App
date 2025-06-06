# Restaurant Application 🍽️

## 🎨 Material 3 Expressive Design - Complete Implementation

A modern, premium Android restaurant application built with **Material 3 Expressive** design system, featuring vibrant colors, enhanced typography, and professional user experience.

---

## ✨ **MATERIAL 3 EXPRESSIVE FEATURES**

### 🎨 **Enhanced Design System**
- **Vibrant Color Palette**: Pink primary (#E91E63), orange secondary (#FF5722), blue tertiary (#2196F3)
- **Expressive Typography**: Complete Material 3 scale with bold headlines and enhanced readability
- **Modern Components**: LargeTopAppBar, FilledIconButton, FilledTonalButton throughout
- **Premium Cards**: 24dp rounded corners with elevated design and gradient overlays
- **Professional Spacing**: Generous padding (20-32dp) for premium feel
- **Visual Hierarchy**: Clear information architecture with expressive styling

### 📱 **Enhanced Screen Experience**

### 🏠 **Home Screen - Material 3 Expressive**
- **LargeTopAppBar**: Enhanced visual hierarchy with expressive typography
- **Cuisine Carousel**: 300x180dp cards with gradient overlays and 24dp rounded corners
- **FilledIconButton**: Modern language toggle and cart buttons
- **Enhanced Badge**: Professional cart count with improved styling
- **Top Dishes**: Spacious layout with Material 3 typography scale
- **Premium Spacing**: 20dp between sections for breathing room

### 🍽️ **Cuisine Screen - Material 3 Expressive**
- **Professional Navigation**: LargeTopAppBar with FilledIconButton back control
- **Enhanced Dish Cards**: FilledIconButton quantity controls with improved spacing
- **Modern Empty State**: Card-based design with expressive styling
- **Clear Typography**: Material 3 scale for better readability

### 🛒 **Cart Screen - Material 3 Expressive**
- **Premium Summary Card**: Surface variant colors with 24dp corners
- **Enhanced Tax Breakdown**: Professional typography with proper color hierarchy
- **FilledTonalButton**: Modern place order action with 56dp height
- **Expressive Success Dialog**: Rounded corners with enhanced styling
- **Professional Empty State**: Card-based design with call-to-action

### 🌍 Multilingual Support
- **English & Hindi**: Complete UI translation
- **Dynamic Language Switching**: Real-time language toggle

## Technical Implementation

### Architecture
- **MVVM Pattern**: Clean separation of concerns
- **Repository Pattern**: Centralized data management
- **Unidirectional Data Flow**: Predictable state management

### Tech Stack
- **Jetpack Compose**: Modern declarative UI
- **Navigation Compose**: Type-safe navigation
- **ViewModel**: Lifecycle-aware state management
- **Coroutines**: Asynchronous programming
- **OkHttp**: HTTP client for API calls
- **Kotlinx Serialization**: JSON parsing
- **Coil**: Image loading

### API Integration
The app integrates with OneBanc's restaurant APIs:

1. **get_item_list**: Fetches cuisines and dishes with pagination
2. **get_item_by_id**: Retrieves specific dish details
3. **get_item_by_filter**: Filters dishes by cuisine, price, rating
4. **make_payment**: Processes order payments

### Data Models
- **Cuisine**: Restaurant cuisine categories
- **Item**: Individual dishes with details
- **CartItem**: Shopping cart items with quantities
- **CartSummary**: Order totals with tax calculations

## Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11 or higher
- Android SDK 35 or higher
- Device/Emulator with API 35+

### Installation
1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd OneBanc
   ```

2. **Open in Android Studio**
   - File → Open → Select the OneBanc folder
   - Wait for Gradle sync to complete

3. **Configure Dependencies**
   The app uses the following key dependencies:
   ```kotlin
   implementation("androidx.navigation:navigation-compose:2.7.6")
   implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
   implementation("com.squareup.okhttp3:okhttp:4.12.0")
   implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
   implementation("io.coil-kt:coil-compose:2.5.0")
   ```

4. **API Configuration**
   The app is pre-configured with OneBanc's test API:
   - Base URL: `https://uat.onebanc.ai`
   - API Key: `uonebancservceemultrS3cg8RaL30`

5. **Build and Run**
   - Click the "Run" button in Android Studio
   - Or use: `./gradlew assembleDebug`

### Permissions
The app requires the following permissions:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## Project Structure

```
app/src/main/java/com/example/onebanc/
├── MainActivity.kt                 # Entry point
├── data/
│   ├── api/
│   │   └── RestaurantApiService.kt # API client
│   ├── models/
│   │   └── RestaurantModels.kt     # Data models
│   └── repository/
│       └── RestaurantRepository.kt # Data layer
├── navigation/
│   └── RestaurantNavigation.kt     # Navigation setup
├── ui/
│   ├── components/
│   │   └── CommonComponents.kt     # Reusable UI components
│   ├── screens/
│   │   ├── HomeScreen.kt          # Home screen
│   │   ├── CuisineScreen.kt       # Cuisine details
│   │   └── CartScreen.kt          # Shopping cart
│   └── theme/                     # Material Design theme
└── viewmodel/
    └── RestaurantViewModel.kt      # State management
```

## Key Features Implementation

### 🔄 State Management
```kotlin
// ViewModel manages app state
class RestaurantViewModel : ViewModel() {
    var uiState by mutableStateOf(RestaurantUiState())
    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
    var currentLanguage by mutableStateOf("English")
}
```

### 🌐 API Integration
```kotlin
// Repository pattern for data access
class RestaurantRepository {
    private val apiService = RestaurantApiService()
    
    suspend fun getItemList(): Result<GetItemListResponse>
    suspend fun makePayment(): Result<MakePaymentResponse>
}
```

### 🎨 UI Components
```kotlin
// Reusable components with Material Design
@Composable
fun CuisineCard(cuisine: Cuisine, onClick: () -> Unit)
@Composable
fun DishCard(item: Item, onAddToCart: () -> Unit)
```

### 🛒 Cart Logic
```kotlin
// Tax calculation and order summary
fun getCartSummary(): CartSummary {
    val subtotal = cartItems.sumOf { it.price * it.quantity }.toDouble()
    val cgst = subtotal * 0.025
    val sgst = subtotal * 0.025
    val total = subtotal + cgst + sgst
    return CartSummary(items, subtotal, cgst, sgst, total)
}
```

## Testing

### Manual Testing Checklist
- [ ] App launches successfully
- [ ] Cuisines load and display properly
- [ ] Horizontal scrolling works for cuisine cards
- [ ] Dish cards show correct information
- [ ] Add to cart functionality works
- [ ] Cart badge updates correctly
- [ ] Language switching works
- [ ] Navigation between screens works
- [ ] Cart calculations are accurate
- [ ] Order placement completes successfully

### Error Handling
- Network connectivity issues
- API error responses
- Empty states (no items, empty cart)
- Loading states during API calls

## Performance Considerations

- **Image Loading**: Coil library with proper caching
- **List Performance**: LazyColumn for efficient scrolling
- **State Management**: Minimal recompositions
- **Memory Management**: Proper lifecycle handling

## Future Enhancements

- [ ] User authentication
- [ ] Order history
- [ ] Favorites functionality
- [ ] Search and filtering
- [ ] Push notifications
- [ ] Offline support
- [ ] Unit and integration tests
- [ ] CI/CD pipeline

## API Documentation

### Headers Required
```
X-Partner-API-Key: uonebancservceemultrS3cg8RaL30
X-Forward-Proxy-Action: <action_name>
Content-Type: application/json
```

### Response Format
All APIs return consistent response format:
```json
{
  "response_code": 200,
  "outcome_code": 200,
  "response_message": "Success message",
  "data": { ... }
}
```

## Troubleshooting

### Common Issues
1. **Build Failures**: Ensure all dependencies are properly synced
2. **API Errors**: Check network connectivity and API key
3. **UI Issues**: Verify Compose compiler version compatibility
4. **Navigation Issues**: Check navigation graph setup

### Debug Tips
- Use Android Studio debugger for step-by-step debugging
- Check Logcat for runtime errors
- Use network inspector for API call debugging
- Test on different screen sizes and orientations

---

**Developed with ❤️ using Jetpack Compose**
