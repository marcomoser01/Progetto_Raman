/* eslint-disable @typescript-eslint/no-unused-vars */

import { Product, ProductWithAVGVote, RatingInterface, User } from "@/lib/types"

type CreatedProduct = Omit<Product, "id">;

const RATING_URL: string = import.meta.env.VITE_RATING_URL
const CATALOG_URL: string = import.meta.env.VITE_CATALOG_URL
const USERS_URL: string = import.meta.env.VITE_USERS_URL

//returns a single rating for a specified product
export async function fetchRatings(productId: number) {
  console.log('fetch ratings')
  const response = await fetch(RATING_URL + `/ratings/${productId}`, {
    mode: 'cors',
  })
  if (!response.ok) {
    console.warn(response.status, response.statusText)
    return
  }

  const responseObj = await response.json()
  console.log(responseObj)
  if (!Array.isArray(responseObj) && Object.keys(responseObj).length === 0) {
    return
  }
  // console.log(responseObj)
  // console.log(responseObj.content)

  return responseObj
}

//returns an array of products
export async function fetchPopularProducts(): Promise<ProductWithAVGVote[] | undefined> {
  console.log('fetch popular products')
  const response = await fetch(RATING_URL + `/popular`, {
    mode: 'cors',
  })

  if (!response.ok) {
    console.warn(response.status, response.statusText)
    return
  }

  const responseObj = await response.json()
  if (Object.keys(responseObj).length === 0) {
    return
  }
  console.log(responseObj)

  return responseObj || undefined
}

//returns the rating created
export async function fetchAddRatingToProduct(productId: number, userId: number, vote: number, message: string): Promise<RatingInterface | undefined> {
  console.log('adding rating')
  //
  const response = await fetch(RATING_URL + `/ratings/${productId}/${userId}`, {
    method: 'POST',
    mode: 'cors',
    body: JSON.stringify({
      message: message,
      vote: vote,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
  if (!response.ok) {
    console.warn(response.status + response.statusText)
    return
  }

  const responseObj = await response.json()
  console.log(responseObj);

  if (Object.keys(responseObj).length === 0) {
    return
  }

  return responseObj || undefined
}

//returns the product created
export async function fetchAddProduct(product: CreatedProduct): Promise<Product | undefined> {
  console.log('adding product')
  const response = await fetch(CATALOG_URL + `/addProduct`, {
    method: 'POST',
    mode: 'cors',
    body: JSON.stringify({
      title: product.title,
      quantity: product.quantity,
      category: product.category,
      price: product.price,
      description: product.description
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
  if (!response.ok) {
    console.warn(response.status + response.statusText)
    return
  }

  const responseObj = await response.json()
  if (Object.keys(responseObj).length === 0) {
    return
  }

  return responseObj || undefined
}

//returns a user given the id
export async function fetchUser(id: number): Promise<User | undefined> {
  console.log('fetch user')
  const response = await fetch(USERS_URL + `/user/${id}`, {
    mode: 'cors',
  })
  console.log(response)
  if (!response.ok) {
    console.warn(response.status + response.statusText)
    return
  }

  const responseObj = await response.json()
  console.log(responseObj)
  if (Object.keys(responseObj).length === 0) {
    return
  }

  return responseObj || undefined
}

export async function fetchUsers(): Promise<User[] | undefined> {
  console.log('fetch users list')
  const response = await fetch(USERS_URL + `/users`, {
    mode: 'cors',
  })

  console.log(response)
  if (!response.ok) {
    console.warn(response.status + response.statusText)
    return
  }

  const responseObj = await response.json()
  console.log(responseObj)
  if (Object.keys(responseObj).length === 0) {
    return
  }

  return responseObj || undefined
}

export async function fetchAddUser(user: Omit<User, "id">): Promise<User | undefined> {

  console.log('adding user')
  const response = await fetch(USERS_URL + `/addUser`, {
    method: 'POST',
    mode: 'cors',
    body: JSON.stringify({
      name: user.name,
      cognome: user.cognome,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
  if (!response.ok) {
    console.warn(response.status + response.statusText)
    return
  }

  const responseObj = await response.json()
  if (Object.keys(responseObj).length === 0) {
    return
  }

  return responseObj || undefined
}