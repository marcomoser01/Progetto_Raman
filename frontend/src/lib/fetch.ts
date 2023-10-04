/* eslint-disable @typescript-eslint/no-unused-vars */

import { Product } from "./types"

const BASE_URL = import.meta.env.VITE_BASE_URL

//returns a single rating for a specified product
export async function fetchRatings(productId: number) {
  const response = await fetch(BASE_URL + `/ratings/${productId}`, {
    mode: 'cors',
  })
  if (!response.ok) {
    console.warn(response.status, response.statusText)
    return
  }

  return (await response.json()) || undefined
}

//returns an array of products
export async function fetchPopularProducts(): Promise<Product[] | undefined> {
  const response = await fetch(BASE_URL + `/popular`, {
    mode: 'cors',
  })
  if (!response.ok) {
    console.warn(response.status, response.statusText)
    return
  }

  return (await response.json()) || undefined
}
