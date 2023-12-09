/* eslint-disable @typescript-eslint/no-unused-vars */
export interface Product {
  id: number
  category: string
  description: string
  price: number
  quantity: number
  title: string
}

export interface ProductWithAVGVote {
  avgVote: number
  product: Product
}

export interface RatingObj {
  rating: RatingInterface
}

export interface RatingInterface {
  id: number
  vote: number
  message: string
  userId: number
  productId: number
}

export interface User {
  id: number
  name: string
  cognome: string //(╯°□°）╯︵ ┻━┻
}
