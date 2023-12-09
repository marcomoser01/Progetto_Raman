import {
	NavigationMenu,
	NavigationMenuItem,
	NavigationMenuLink,
	NavigationMenuList,
	navigationMenuTriggerStyle,
} from '@/components/ui/navigation-menu'

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
						to test
					</NavigationMenuLink>
				</NavigationMenuItem>
			</NavigationMenuList>
		</NavigationMenu>
	)
}

export default MyNav
