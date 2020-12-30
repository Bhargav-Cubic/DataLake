import { BrowserModule } from '@angular/platform-browser';
import { NgModule,CUSTOM_ELEMENTS_SCHEMA,NO_ERRORS_SCHEMA } from '@angular/core';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { FormGroup, FormControl } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown/angular2-multiselect-dropdown';





import { AppComponent } from './app.component';
import { AuthGuard } from './auth/auth.guard';
import { AuthService } from './auth/auth.service';
import { AppMaterialModule } from './app-material/app-material.module';


import { UserAccessFormComponent } from './user-access-form/user-access-form.component';
import { UserAccessFormModel } from './user-access-form/user-access-form-model';
import { UserAccessFormService } from './user-access-form/user-access-form.service';
import { HotelFileUploadComponent } from './hotel-file-upload/hotel-file-upload.component';
import { UploadFileService } from './hotel-file-upload/hotel-file-upload.service';
import { AddPccComponent } from './add-pcc/add-pcc.component';
import { AddPccService } from './add-pcc/add-pcc.service';
import { UploadHNSService } from './manage-hns/manage-hns.service';
import { ManageEmpComponent } from './manage-emp/manage-emp.component';
import { EmpHierService } from './manage-emp/manage-emp.service';


import { ManageHnsComponent } from './manage-hns/manage-hns.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { EnvironmentAccessModel } from './environment-access/environment-access-model';
import { EnvironmentAccessService } from './environment-access/environment-access.service';

import './rxjs-operators';
import { EnvironmentAccessComponent } from './environment-access/environment-access.component';
import { FieldErrorDisplayComponent } from './field-error-display/field-error-display.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { LoginFormService } from './login/login.component.service';
import { DlFileUploadComponent } from './dl-file-upload/dl-file-upload.component';
import {DlFileUploadService} from './dl-file-upload/dl-file-upload.service';


export const appRoutes: Routes = [

    { path: '', component: HomeComponent, canActivate: [AuthGuard] },

    {
        path: 'login',
        component: LoginComponent

    },

    {
        path: 'add-user',
        component: UserAccessFormComponent, canActivate: [AuthGuard]
    },

    {
        path: 'hotel-file-upload',
        component: HotelFileUploadComponent, canActivate: [AuthGuard]
    },

    {
        path: 'add-pcc',
        component: AddPccComponent, canActivate: [AuthGuard]

    },
    {
        path: 'manage-emp',
        component: ManageEmpComponent, canActivate: [AuthGuard]

    },
    {
        path: 'manage-hns',
        component: ManageHnsComponent, canActivate: [AuthGuard]

    },
    {
        path: 'SelfServiceData',
        component: DlFileUploadComponent, canActivate: [AuthGuard]

    },
    {
        path: '**',
        component: HomeComponent, canActivate: [AuthGuard]

    },
    {
        path: 'home',
        component: HomeComponent, canActivate: [AuthGuard]

    },



]


@NgModule({
    declarations: [
        AppComponent,
        UserAccessFormComponent,
        HotelFileUploadComponent,
        AddPccComponent,
        LoginComponent,
        ManageEmpComponent,
        ManageHnsComponent,
        DashboardComponent,
        HomeComponent,
        EnvironmentAccessComponent,
        FieldErrorDisplayComponent,
        DlFileUploadComponent



    ],
    imports: [
        BsDropdownModule.forRoot(),
        BrowserModule,
        RouterModule.forRoot(appRoutes),
        HttpClientModule,
        HttpModule,
        TooltipModule.forRoot(),
        ModalModule.forRoot(),
        MDBBootstrapModule.forRoot(),
        ReactiveFormsModule,
        FormsModule,
        BrowserAnimationsModule,
        AngularMultiSelectModule
     

    ],
    exports: [RouterModule],
    providers: [UploadFileService,DlFileUploadService,
        AddPccService,
        UserAccessFormService, AuthService, AuthGuard, EnvironmentAccessService, LoginFormService, LoginComponent, EmpHierService],
        schemas: [
            CUSTOM_ELEMENTS_SCHEMA,
            NO_ERRORS_SCHEMA
          ],
    bootstrap: [AppComponent]
})
export class AppModule { }