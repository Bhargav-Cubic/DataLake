import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './../auth/auth.service';
import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  isLoggedIn$: Observable<boolean>;
  isPccActive$:Observable<boolean>;
  isHNSActive$:Observable<boolean>;
  isUserAccessActive$:Observable<boolean>;
  isDlFileupLoadActive$:Observable<boolean>

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
    this.isPccActive$ = this.authService.isPccActive;
    this.isHNSActive$ = this.authService.isHNSActive;
    this.isUserAccessActive$=this.authService.isUserAccessActive;
    this.isDlFileupLoadActive$=this.authService.isDlFileUploadActive;
    
  }

  onLogout(){
    this.authService.logout();
  }

}
