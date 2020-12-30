import { Component } from '@angular/core';
import { Router, Routes, NavigationEnd } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { window } from 'rxjs/operator/window';
import { PlatformLocation } from '@angular/common';
import { AuthService } from './auth/auth.service';
import { User } from './auth/user';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    title = 'Global Bussiness  Travel';


    constructor(private router: Router, private location: PlatformLocation, private authService: AuthService) {

        location.onPopState(() => {

            console.log("pressing back !")
            if (this.router.url == '/home') {


               location.forward();


            }



        });

      
    }

    ngOnInit() {

        this.router.navigate(['']);
        



    }













}


