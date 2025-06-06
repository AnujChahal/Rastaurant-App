package com.example.assignonebanc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignonebanc.data.models.Item
import com.example.assignonebanc.ui.components.CompatFilledIconButton
import com.example.assignonebanc.ui.components.DishCard
import com.example.assignonebanc.ui.components.ErrorScreen
import com.example.assignonebanc.ui.components.LoadingScreen
import com.example.assignonebanc.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuisineScreen(
    viewModel: RestaurantViewModel,
    cuisineId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState
    val cartItems = viewModel.cartItems
    val currentLanguage = viewModel.currentLanguage
    
    // Load cuisine items when screen is first displayed
    LaunchedEffect(cuisineId) {
        viewModel.loadCuisineItems(cuisineId)
    }
    
    // Get cuisine name
    val cuisine = uiState.cuisines.find { it.cuisineId == cuisineId }
    val cuisineName = cuisine?.cuisineName ?: "Cuisine"
    
    // Show loading screen
    if (uiState.isLoading) {
        LoadingScreen()
        return
    }
      // Show error screen
    if (uiState.error != null) {
        ErrorScreen(
            error = uiState.error,
            onRetry = { viewModel.refreshData() }
        )
        return
    }
      Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = cuisineName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )                },
                navigationIcon = {
                    CompatFilledIconButton(
                        onClick = onBackClick,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            item {
                Text(
                    text = if (currentLanguage == "English") 
                        "Select dishes to order" 
                    else 
                        "ऑर्डर करने के लिए व्यंजन चुनें",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
            
            items(uiState.selectedCuisineItems) { dish ->
                val cartItem = cartItems.find { it.itemId == dish.id }
                val quantity = cartItem?.quantity ?: 0
                
                DishCard(
                    item = dish,
                    quantity = quantity,                    onAddToCart = { 
                        viewModel.addToCart(cuisineId, dish) 
                    },
                    onRemoveFromCart = { 
                        viewModel.removeFromCart(dish.id) 
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
              if (uiState.selectedCuisineItems.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 32.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(40.dp),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(
                                text = if (currentLanguage == "English") 
                                    "No dishes available" 
                                else 
                                    "कोई व्यंजन उपलब्ध नहीं",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
