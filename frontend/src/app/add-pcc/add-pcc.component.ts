import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { Location } from '@angular/common';
import { AddPccService } from './add-pcc.service';
import { AddPCCModel } from './add-pcc-model';
import { Response } from '@angular/http/src/static_response';
import { DISABLED } from '@angular/forms/src/model';


@Component({
    selector: 'app-add-pcc',
    templateUrl: './add-pcc.component.html',
    styleUrls: ['./add-pcc.component.scss']
})
export class AddPccComponent implements OnInit {


    pccform: FormGroup;

    addPCCModel = new AddPCCModel;
    submitted = false;
    status: boolean = true;
    visible: boolean = false;

    responseLabel: string = ""
    constructor(private addPccService: AddPccService, private formBuilder: FormBuilder) { }


    ngOnInit() {

        this.pccform = this.formBuilder.group({
            pcc: [null, Validators.required],
            UploadedBy: [null, Validators.required]
        });

    }
    isFieldValid(field: string) {
        return !this.pccform.get(field).valid && this.pccform.get(field).touched;
    }

    displayFieldCss(field: string) {
        return {
            'has-error': this.isFieldValid(field),
            'has-feedback': this.isFieldValid(field)
        };
    }

    onSubmit() {

        this.responseLabel="";

        if(this.pccform.valid){

        console.log('entered into the submit function');
        this.addPCCModel.pcc=this.pccform.value.pcc;
        this.addPCCModel.uploaded_by=this.pccform.value.UploadedBy;

        this.addPccService.create(this.addPCCModel).subscribe(event => {
            this.visible = true;
            this.status = event.status;
            this.responseLabel = event.message;
        },
            (err) => {
                console.log('in error');
            });

            this.pccform.reset();
    }else{

        this.validateAllFormFields(this.pccform);
    }
}

    validateAllFormFields(formGroup: FormGroup) {
        Object.keys(formGroup.controls).forEach(field => {
            const control = formGroup.get(field);
            if (control instanceof FormControl) {
                control.markAsTouched({ onlySelf: true });
            } else if (control instanceof FormGroup) {
                this.validateAllFormFields(control);
            }
        });
    }

    




}
