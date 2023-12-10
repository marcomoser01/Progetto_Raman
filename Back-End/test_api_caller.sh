#!/bin/bash

# Funzione per eseguire la chiamata cURL senza specificare Content-Type, stampare lo stato HTTP e salvare la risposta in una directory specifica
make_curl_request_without_content_type() {
    local response_file="$1"
    local method="$2"
    local url="$3"

    echo "Chiamata senza Content-Type a: $method $url"
    http_code=$(curl -w "%{http_code}" -X "$method" -o "api_test_result/$response_file" -s "$url")
    echo "Stato HTTP: $http_code"
    echo "Risultati della chiamata salvati in: api_test_result/$response_file"
}

# Funzione per eseguire la chiamata cURL con Content-Type specifico, stampare lo stato HTTP e salvare la risposta in una directory specifica
make_curl_request_with_content_type() {
    local response_file="$1"
    local method="$2"
    local url="$3"
    local data="$4"

    echo "Chiamata con Content-Type a: $method $url"
    http_code=$(curl -w "%{http_code}" -X "$method" -o "api_test_result/$response_file" -H "Content-Type: application/json" "$url" -d "$data")
    echo "Stato HTTP: $http_code"
    echo "Risultati della chiamata salvati in: api_test_result/$response_file"
}

#------------------------------ CATALOG ------------------------------

# Chiamata "addProduct" nella categoria "Catalog"
make_curl_request_with_content_type "catalog/add_product_response.json" POST "http://localhost:9999/catalog/api/addProduct" '{"title": "Borraccia", "category": "Alimentare", "description": "La borraccia è gialla", "price": 20, "quantity": 30}'

# Chiamata "addProducts" nella categoria "Catalog"
make_curl_request_with_content_type "catalog/add_products_response.json" POST "http://localhost:9999/catalog/api/addProducts" '[ { "title": "Cubo di Rubik 3x3x3", "category": "Divertimento", "description": "Il cubo è adatto per gli speedcuber", "price": 25, "quantity": 20 }, { "title": "Cubo di Rubik 4x4x4", "category": "Divertimento", "description": "Il cubo è adatto per gli speedcuber", "price": 35, "quantity": 10 }, { "title": "Cubo di Rubik 5x5x5", "category": "Divertimento", "description": "Il cubo è adatto per gli speedcuber", "price": 30, "quantity": 40 } ]'

# Chiamata "info" nella categoria "Catalog"
make_curl_request_without_content_type "catalog/info_response.json" "GET" "http://localhost:9999/catalog/api/info"

# Chiamata "products" nella categoria "Catalog"
make_curl_request_without_content_type "catalog/products_response.json" "GET" "http://localhost:9999/catalog/api/products"

# Chiamata "product/{id}" nella categoria "Catalog"
make_curl_request_without_content_type "catalog/product_id_2_response.json" "GET" "http://localhost:9999/catalog/api/product/2"

# Chiamata "products/category/{category}" nella categoria "Catalog"
make_curl_request_without_content_type "catalog/products_category_1_response.json" "GET" "http://localhost:9999/catalog/api/products/category/1"

# Chiamata "products/{id}/availability/{amount}" nella categoria "Catalog"
make_curl_request_with_content_type "catalog/update_product_availability_response.json" PUT "http://localhost:9999/catalog/api/products/2/availability/-15"


#------------------------------ USER ------------------------------

# Chiamata "addUser" per l'entità "User"
make_curl_request_with_content_type "user/add_user_response.json" "POST" "http://localhost:9999/user/api/addUser" '{ "name": "Lorenzo", "cognome": "Sannicolò" }'

# Chiamata "addUsers" per l'entità "User"
make_curl_request_with_content_type "user/add_users_response.json" "POST" "http://localhost:9999/user/api/addUsers" '[ { "name": "Francesco", "cognome": "Santini" }, { "name": "Bledi", "cognome": "Hamzaj" } ]'

# Chiamata "info" per l'entità "User"
make_curl_request_without_content_type "user/info_response.json" "GET" "http://localhost:9999/user/api/info"

# Chiamata "user/{id}" per l'entità "User"
make_curl_request_without_content_type "user/user_id_1_response.json" "GET" "http://localhost:9999/user/api/user/1"

# Chiamata "users" per l'entità "User"
make_curl_request_without_content_type "user/users_response.json" "GET" "http://localhost:9999/user/api/users"

#------------------------------ PURCHASE ------------------------------

# Chiamata "purchases/{userId}" per l'entità "Purchase"
make_curl_request_with_content_type "purchase/purchases_userId_post_response.json" "POST" "http://localhost:9999/purchase/api/purchases/1" '{ "productId": "1", "count": 10 }'

# Chiamata "info" per l'entità "Purchase"
make_curl_request_without_content_type "purchase/info_response.json" "GET" "http://localhost:9999/purchase/api/info"

# Chiamata "purchases/purchase/{purchaseId}" per l'entità "Purchase"
make_curl_request_without_content_type "purchase/purchase_id_1_response.json" "GET" "http://localhost:9999/purchase/api/purchases/purchase/1"

# Chiamata "purchases/{userId}" per l'entità "Purchase"
make_curl_request_without_content_type "purchase/purchases_userId_get_response.json" "GET" "http://localhost:9999/purchase/api/purchases/1"


#------------------------------ RATING ------------------------------

# Chiamata "ratings/{productId}/{userId}" per l'entità "Rating"
make_curl_request_with_content_type "rating/ratings_productId_userId_post_response.json" "POST" "http://localhost:9999/rating/api/ratings/1/1" '{ "vote": 4, "message": "Mi è piaciuto" }'

# Chiamata "info" per l'entità "Rating"
make_curl_request_without_content_type "rating/info_response.json" "GET" "http://localhost:9999/rating/api/info"

# Chiamata "popular" per l'entità "Rating"
make_curl_request_without_content_type "rating/popular_response.json" "GET" "http://localhost:9999/rating/api/popular"

# Chiamata "rating/{id}" per l'entità "Rating"
make_curl_request_without_content_type "rating/rating_id_1_response.json" "GET" "http://localhost:9999/rating/api/rating/1"

# Chiamata "ratings/{productId}" per l'entità "Rating"
make_curl_request_without_content_type "rating/ratings_productId_1_response.json" "GET" "http://localhost:9999/rating/api/ratings/1"

