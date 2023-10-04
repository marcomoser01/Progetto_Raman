export interface Product {
  id: number
  category: string
  description: string
  price: number
  quantity: number
  title: string
}

export interface RatingType {
  id: number
  vote: number
  message: string
  userId: number
  productId: number
}