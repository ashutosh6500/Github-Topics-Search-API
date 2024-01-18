import { Component } from '@angular/core';
import { Globals } from 'src/app/globals';
import { GlobalsService } from 'src/app/services/globals.service';

@Component({
  selector: 'app-nav-bar',
  providers: [ Globals],
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent {
  constructor(public globals : GlobalsService){}
}
