import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { User } from './user';
import { LoginComponent } from '../login/login.component';
import { LoginFormModel } from '../login/login.component.model';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';

@Injectable()
export class AuthService {
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private pccActive: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private hnsActive: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private userAccessFormActive: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private dlfileuploadactive: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  private loginusername:BehaviorSubject<String> = new BehaviorSubject<String>("");

  public user:String="";




  wrongPasswd: String = '';
  loginComponent: LoginComponent;
  loginFormModel: LoginFormModel[];
  form: FormGroup;






  constructor(
    private router: Router
  ) { }


  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  get isPccActive() {
    return this.pccActive.asObservable();
  }

  get isHNSActive() {
    return this.hnsActive.asObservable();
  }

  get isUserAccessActive() {
    return this.userAccessFormActive.asObservable();
  }

  get isDlFileUploadActive() {
    return this.dlfileuploadactive.asObservable();
  }


  get loginUserName(){
    return this.loginusername.asObservable();
  }


  login(mnnames: String[],username) {

    if (this.includes(mnnames, "addpcc")) {
      console.log("PCC Exist");
      this.pccActive.next(true);
    }
    if (this.includes(mnnames, "useraccessform")) {
      console.log("USER ACCESS EXIST");
      this.userAccessFormActive.next(true);
    }
    if (this.includes(mnnames, "hnsfileupload")) {
      console.log("HNS Exist");
      this.hnsActive.next(true);
    }
    if (this.includes(mnnames, "dlfileupload")) {
      console.log("DLFILEUPLOAD EXIST");
      this.dlfileuploadactive.next(true);
    }

    this.loginusername.next(username);
    this.user=this.loginusername.value;
    this.loggedIn.next(true);
    this.router.navigate(['/home']);
  }
  

  /*
  

  
  login(username){
    this.loggedIn.next(true);
    this.pccActive.next(true);
    this.userAccessFormActive.next(true);
    this.hnsActive.next(true);
    this.dlfileuploadactive.next(true);
    this.loginusername.next(username);
    this.user=this.loginusername.value;
    this.router.navigate(['/home']);
    
    console.log("this.loginusername"+this.loginusername.value);
  }


*/


  includes(container, value) {
    var returnValue = false;
    var pos = container.indexOf(value);
    if (pos >= 0) {
      returnValue = true;
    }
    return returnValue;
  }

  doesNotInclude(container, value) {
    var returnValue = false;
    var pos = container.indexOf(value);
    if (pos < 0) {
      returnValue = true;
    }
    return returnValue;
  }


  logout() {
    this.loggedIn.next(false);
    this.pccActive.next(false);
    this.userAccessFormActive.next(false);
    this.hnsActive.next(false);
    this.dlfileuploadactive.next(false);
    this.router.navigate(['/login']);
  }











}
