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
