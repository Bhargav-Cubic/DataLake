import { AuthService } from './../auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { Response } from '@angular/http/src/static_response';
import { LoginFormService } from './login.component.service';
import { LoginFormModel } from './login.component.model';
import { GroupMenuAccessModel } from './GroupMenuAccessModel';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { forEach } from '@angular/router/src/utils/collection';
import { Injectable } from '@angular/core';
import { LoginResponse } from './login.component.response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


export class LoginComponent implements OnInit {
  

  form: FormGroup;
  private formSubmitAttempt: boolean;
  loginFormModel: LoginFormModel[];
  loginResponse: LoginResponse[]
  groupMenuAccessModel: GroupMenuAccessModel[];
  uname: String = "";
  passwd: String = "";
  grpname: String = "";
  menuitem: String[] = [];
  loginResponseMessage: String = ""
  


  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private loginFormService: LoginFormService
  ) { }

  ngOnInit() {

    this.form = this.formBuilder.group({
      userName: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  isFieldValid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }

  onSubmit() {
    if (this.form.valid) {
     // this.authService.login(this.form.value.userName);
      this.getUserGroupAccess(this.form.value.userName, this.form.value.password);
    }

    this.validateAllFormFields(this.form);
    this.formSubmitAttempt = true;
  }
  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      console.log(field);
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }


   getGroupMenuAccess(username: String):void {
    this.loginFormService.getGroupMenuAccess(username).subscribe(resultArray => {
      //this.groupMenuAccessModel = resultArray;
      //console.log("value inside list are "+this.groupMenuAccessModel)
      
      //for (let value of this.groupMenuAccessModel) {
      for(let value of resultArray){
        console.log("value"+value);
        //this.menuitem.push(value.menuname);
        this.menuitem.push(value.replace(/\s/g, "").toLowerCase());
        console.log("value.menuname"+value)
        console.log("menuitem"+this.menuitem);
      }

           
      console.log("this.menuitem from login componet" );
      this.authService.login(this.menuitem,this.form.value.userName);
      
     
    })
    
    
    
  }

  


  getUserGroupAccess(username, password): void {
    this.loginFormService.getUserGroupAccess(username, password).subscribe(resultArray => {
      this.loginResponse = resultArray;
      console.log("testing username and password");
      console.log(this.loginResponse);

      if(this.loginResponse){
        //this.authService.login();
        this.getGroupMenuAccess(username);
      }else{
        console.log("username and password authentication failed");
        this.loginResponseMessage = "Invalid user name and password";
      }






      //for (let value of this.loginFormModel) {
       // this.uname = value.userName;
      //  this.passwd = value.passWord;
       // this.grpname = this.grpname + "," + value.groupname;
     // }

    //  if (this.uname == this.form.value.userName && this.passwd == this.form.value.password) {

       // this.getGroupMenuAccess(this.grpname);
        //this.authService.login();
//
      //} else {
        
      //  

     // }

    },
      error => console.log("Error :: " + error));
  }

  get menunames(){
    return this.menuitem;
  }


 }
