import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.scss']
})
export class MenuBarComponent {

  menu: Array<MenuItem> = [
    {
      label: 'Home',
      icon: 'pi-home'
    },
    {
      label: 'Trending',
      icon: 'pi-chart-line'
    },
    {
      label: 'Explore',
      icon: 'pi-compass'
    },
    {
      label: 'Favourites',
      icon: 'pi-star'
    },
    {
      label: 'Settings',
      icon: 'pi-cog'
    },
  ];
}
