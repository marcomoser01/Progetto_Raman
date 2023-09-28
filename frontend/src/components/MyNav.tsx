import {
	NavigationMenu,
	NavigationMenuContent,
	NavigationMenuIndicator,
	NavigationMenuItem,
	NavigationMenuLink,
	NavigationMenuList,
	NavigationMenuTrigger,
	NavigationMenuViewport,
	navigationMenuTriggerStyle,
} from '@/src/components/ui/navigation-menu'

function MyNav() {
	return (
		<NavigationMenu>
			<NavigationMenuList>
				{/* <NavigationMenuItem>
					<NavigationMenuTrigger>Item Two</NavigationMenuTrigger>
					<NavigationMenuContent>
						<NavigationMenuLink>Link2</NavigationMenuLink>
					</NavigationMenuContent>
				</NavigationMenuItem> */}

				<NavigationMenuItem>
					<NavigationMenuLink href="#" className={navigationMenuTriggerStyle()}>
						Home
					</NavigationMenuLink>
				</NavigationMenuItem>

				<NavigationMenuItem>
					<NavigationMenuLink href="#" className={navigationMenuTriggerStyle()}>
						Categorie
					</NavigationMenuLink>
				</NavigationMenuItem>

				<NavigationMenuItem>
					<NavigationMenuLink href="#" className={navigationMenuTriggerStyle()}>
						Prodotti
					</NavigationMenuLink>
				</NavigationMenuItem>
			</NavigationMenuList>
		</NavigationMenu>
	)
}

export default MyNav
