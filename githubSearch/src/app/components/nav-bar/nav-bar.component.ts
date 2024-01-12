import { Component } from '@angular/core';
import { Globals } from 'src/app/globals';

@Component({
  selector: 'app-nav-bar',
  providers: [ Globals],
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent {
  constructor(public globals : Globals){}
}
