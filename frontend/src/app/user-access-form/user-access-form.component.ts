import { Component, OnInit } from '@angular/core';
import { UserAccessFormModel } from './user-access-form-model';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { UserAccessFormService } from './user-access-form.service';
import { NgIf } from '@angular/common/src/directives/ng_if';
import { environment } from '../../environments/environment';
import { EnvironmentAccessComponent } from '../environment-access/environment-access.component';

import { EnvironmentAccessModel } from '../environment-access/environment-access-model';
import { EnvironmentAccessService } from '../environment-access/environment-access.service';

import { PipeTransform, Pipe } from '@angular/core';
import { forEach } from '@angular/router/src/utils/collection';


@Component({
    selector: 'app-user-access-form',
    templateUrl: './user-access-form.component.html',
    styleUrls: ['./user-access-form.component.scss']
})
export class UserAccessFormComponent implements OnInit {

    useraccessform: FormGroup;


    userAccessFormModel = new UserAccessFormModel;
    visible: boolean = false;
    status: boolean = false;
    responseLabel: string = "";
    submitted = false;
    dev_select: number = 0;
    prod_select: number = 0;
    sbox_select: number = 0;
    dropdownList_dev = [];
    dropdownList_prod = [];
    dropdownList_sbox = [];
    selectedItems = [];
    dropdownSettings_dev = {};
    dropdownSettings_prod = {};
    dropdownSettings_sbox = {};
    environmentAccessModel: EnvironmentAccessModel[];
    env_valid: boolean = true;
    env_touched: boolean = false;
    selectedItems_dev=[];
    selectedItems_prod=[];
    selectedItems_sbox=[];



    constructor(private formBuilder: FormBuilder, private userAccessFormService: UserAccessFormService, private environmentAccessService: EnvironmentAccessService) {

    }



    ngOnInit() {
        this.useraccessform = this.formBuilder.group({
            GBTID:[null,Validators.required],
            FirstName: [null, Validators.required],
            LastName: [null, Validators.required],
            Email: [null, Validators.required],
            Department:'',
            JobDescription:'',
            UserType: [null, Validators.required],
            PIIAccess: [null, Validators.required],
            Reason:'',
            RequestedBy: [null, Validators.required],
            Development: '',
            Production: '',
            Sandbox: ''
        });
       
     
   



   



        this.getenvAccess();

        this.dropdownSettings_dev = {
            singleSelection: false,
            text: "select Development Schema",
            selectAllText: 'Select All',
            unSelectAllText: 'UnSelect All',
            enableSearchFilter: true,
            classes: ""
        };
        this.dropdownSettings_prod = {
            singleSelection: false,
            text: "Select Production Schema   ",
            selectAllText: 'Select All',
            unSelectAllText: 'UnSelect All',
            enableSearchFilter: true,
            classes: ""
        };
        this.dropdownSettings_sbox = {
            singleSelection: false,
            text: "Select Sandbox Schema         ",
            selectAllText: 'Select All',
            unSelectAllText: 'UnSelect All',
            enableSearchFilter: true,
            classes: ""
        };

    }
    isFieldValid(field: string) {
        return !this.useraccessform.get(field).valid && this.useraccessform.get(field).touched;
    }

    displayFieldCss(field: string) {
        return {
            'has-error': this.isFieldValid(field),
            'has-feedback': this.isFieldValid(field)
        };
    }

    onSubmit() {
        this.responseLabel='';
        if (this.useraccessform.valid) {

            if (this.env_touched) {
                this.env_valid = true;
                this.validate_env();
                console.log("FINAL VALIDATION:"+this.env_valid)
                if (this.env_valid ) {
                    this.env_valid = true;
                    this.userAccessFormModel.gbt_id = this.useraccessform.value.GBTID;
                    this.userAccessFormModel.first_name = this.useraccessform.value.FirstName;
                    this.userAccessFormModel.last_name = this.useraccessform.value.LastName;
                    this.userAccessFormModel.email = this.useraccessform.value.Email;
                    this.userAccessFormModel.department = this.useraccessform.value.Department;
                    this.userAccessFormModel.job_description = this.useraccessform.value.JobDescription;
                    this.userAccessFormModel.requested_by = this.useraccessform.value.RequestedBy;
                    this.userAccessFormModel.user_type = this.useraccessform.value.UserType;
                    this.userAccessFormModel.pii_access = this.useraccessform.value.PIIAccess;
                    this.userAccessFormModel.development = this.useraccessform.value.Development;
                    this.userAccessFormModel.production = this.useraccessform.value.Production;
                    this.userAccessFormModel.sandbox = this.useraccessform.value.Sandbox;
                    this.userAccessFormModel.reason = this.useraccessform.value.Reason;


                    if (typeof this.userAccessFormModel.development !== 'undefined') {
                        this.userAccessFormModel.development = "";
                        for (let item of this.useraccessform.value.Development) {
                            this.userAccessFormModel.development = this.userAccessFormModel.development + item.itemName + ",";
                        }
                        this.userAccessFormModel.development = this.userAccessFormModel.development.slice(0, -1);

                    }


                    if (typeof this.userAccessFormModel.production !== 'undefined') {
                        this.userAccessFormModel.production = "";
                        for (let item of this.useraccessform.value.Production) {
                            this.userAccessFormModel.production = this.userAccessFormModel.production + item.itemName + ",";
                        }
                        this.userAccessFormModel.production = this.userAccessFormModel.production.slice(0, -1);

                    }

                    if (typeof this.userAccessFormModel.sandbox !== 'undefined') {
                        this.userAccessFormModel.sandbox = "";
                        for (let item of this.useraccessform.value.Sandbox) {
                            this.userAccessFormModel.sandbox = this.userAccessFormModel.sandbox + item.itemName + ",";
                        }
                        this.userAccessFormModel.sandbox = this.userAccessFormModel.sandbox.slice(0, -1);

                    }


                    //api call
                    this.userAccessFormService.create(this.userAccessFormModel).subscribe(event => {
                        this.visible = true;
                        this.status = event.status;
                        this.responseLabel = event.message;
                    },
                        (err) => {
                            console.log('in error');
                        });;

                    this.reset();
                } else {
                    this.env_valid = false;
                }
            } else {
                this.env_valid = false;
            }
        } else {
            this.validateAllFormFields(this.useraccessform);
            this.env_valid=false;
        }

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

    reset() {
        this.useraccessform.reset();

    }


    onChange_dev($event) {
        this.dropdownList_dev = []
        for (let value of this.environmentAccessModel) {
            if (value.environmentName == "development") {
                var obj = { "id": value.sequence_id, "itemName": value.schemaName }
                this.dropdownList_dev.push(obj);
            }
        }

    }
    onChange_prod($event) {
        this.dropdownList_prod = []
        for (let value of this.environmentAccessModel) {
            if (value.environmentName == "production") {
                var obj = { "id": value.sequence_id, "itemName": value.schemaName }
                this.dropdownList_prod.push(obj);
            }
        }

    }
    onChange_sbox($event) {
        this.dropdownList_sbox = []
        for (let value of this.environmentAccessModel) {
            if (value.environmentName == "sandbox") {
                var obj = { "id": value.sequence_id, "itemName": value.schemaName }
                this.dropdownList_sbox.push(obj);
            }
        }

    }
    getenvAccess(): void {
        this.environmentAccessService.getEnvAccess().subscribe(resultArray => {
            this.environmentAccessModel = resultArray
        },
            error => console.log("Error :: " + error));
    }

    //development
    onItemSelect_dev(item: any) {
        console.log(item);
        this.dev_select++;
        console.log(this.dev_select)
        this.env_touched = true;
        this.validate_env();

    }
    OnItemDeSelect_dev(item: any) {
        console.log(item);
        this.dev_select--;
        console.log(this.dev_select)
        this.validate_env();
    }
    onSelectAll_dev(items: any) {
        this.dev_select = 0
        console.log("all select items");
        console.log(items);
        this.dev_select = this.dropdownList_dev.length;
        console.log(this.dev_select)
        this.env_touched = true;
        this.validate_env();
    }
    onDeSelectAll_dev(items: any) {
        console.log(items);
        this.dev_select = 0
        this.validate_env();
    }


    //production
    onItemSelect_prod(item: any) {
        console.log(item);
        this.prod_select++;
        console.log(this.prod_select)
        this.env_touched = true;
        this.validate_env();
    }
    OnItemDeSelect_prod(item: any) {
        console.log(item);
        this.prod_select--;
        console.log(this.prod_select)
        this.validate_env();
    }
    onSelectAll_prod(items: any) {
        console.log(items);
        this.prod_select = 0;
        this.prod_select = this.dropdownList_prod.length;
        this.env_touched = true;
        this.validate_env();
    }
    onDeSelectAll_prod(items: any) {
        console.log(items);
        this.prod_select = 0;
        this.validate_env();
    }

    //sandbox

    onItemSelect_sbox(item: any) {
        console.log(item);
        this.sbox_select++;
        console.log(this.sbox_select)
        this.env_touched = true;
        this.validate_env();
    }
    OnItemDeSelect_sbox(item: any) {
        console.log(item);
        this.sbox_select--;
        console.log(this.sbox_select)
        this.validate_env();
    }
    onSelectAll_sbox(items: any) {
        console.log(items);
        this.sbox_select = 0;
        this.sbox_select = this.dropdownList_sbox.length;
        this.env_touched = true;
        this.validate_env();
    }
    onDeSelectAll_sbox(items: any) {
        console.log(items);
        this.sbox_select = 0;
        this.validate_env();
    }

    validate_env() {
        if (this.dev_select == 0 && this.prod_select == 0 && this.sbox_select == 0) {
            console.log("invalid")
            this.env_valid = false;
           
        } else {
            console.log("valid");
            this.env_valid = true;
           
        }
    }
  







}
