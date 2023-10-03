# Inside the _frontend/_ folder

all the package.json scripts can also be run with `npm run x`

**example:** `pnpm dev` = `npm run dev`

## dev environment

1. `git checkout` to branch `frontend`
2. `git pull` to be sure
3. `pnpm i` or `npm i` to install necessary packages
4. run these processes in parallel
   - `pnpm tail` to watch changes in tailwindCSS stuff
   - `pnpm tsc` to perform type checking, live
   - `pnpm dev` to host the project locally on port 5173

## build and preview

- run `pnpm build` to create the dist folder containing all the striclty necessary code
- run `pnpm preview` to preview the project from the dist folder (<http://localhost:4173>)
- run `pnpm both` to build and preview the minified - and possibily more performant - version of this frontend project

### optionally you can

(these are included in `pnpm build`)

- run `pnpm tailBuild` to create the CSS file
- run `pnpm minifyCSS` to minify CSS

> see [package.json](/package.json>)

---

## //TODO api stuff

- set .ENV file with URLS
- `FETCH ALL RATINGS` of a product in /Product
  - GET **/api/ratings/{productId}**
- `POPULAR PRODUCT LIST`
  - fetch some products with higher average vote. Then create a list in "/" with productId, title, price and average vote.
  - GET **/api/popular**
- `ADD RATING`.
  - POST **/api/ratings/{productId}/{userId}**
  - come “body” della richiesta un oggetto con gli
    attributi “voto” (1-5) e “commento”, associa un voto al prodotto. Se per questo
    utente il voto/commento esiste gia’, viene sovrascritto.
