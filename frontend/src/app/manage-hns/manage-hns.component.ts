import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { Location } from '@angular/common';
// import { AddPccService } from './manage-hns.service';

import { UploadHNSService } from './manage-hns.service';
import { MatchHNSModel } from './manage-hns-model';
import { Response } from '@angular/http/src/static_response';
import { DISABLED } from '@angular/forms/src/model';
import { ViewChild } from '@angular/core';
import { HttpModule } from '@angular/http';

@Component({
    selector: 'app-add-pcc',
    templateUrl: './manage-hns.component.html',
    styleUrls: ['./manage-hns.component.scss'],
    providers: [UploadHNSService]
})
export class ManageHnsComponent implements OnInit {


    pccform: FormGroup;

    addHNSModel = new MatchHNSModel;
    submitted = false;
    status: boolean = true;
    visible: boolean = false;
    dropdownList = [];
    selectedItems = [];
    dropdownSettings = {};
    selected: number = 0;
    responseLabel: string = "";
    isUpload : boolean = false;
    isFile : boolean = false;
    isUploadSectionEnabled = false;

    selectedFiles: FileList
    currentFileUpload: File
    btnDisable: boolean = true;
	@ViewChild('fileUpload')
    fileupload: any;
    constructor(private formBuilder: FormBuilder,private uploadService: UploadHNSService) { }

    ngOnInit() {
        this.pccform = this.formBuilder.group({
            pcc: [null, Validators.required],
            UploadedBy: [null, Validators.required]
        });
        this.dropdownList = [
            { id: 0, name: 'Yes' },
            { id: 1, name: 'No' },
          ];
    }
    openManageHNSForm() {
        console.log("\n\n openManageHNSForm isUpload === ",this.isUpload);
        console.log("isFile === ",this.isFile);
        this.isUpload = true;
    }
    openUploadFiles(){
        console.log("\n\n openUploadFiles === ",this.isUpload);
        console.log("isFile === ",this.isFile);
        this.isFile = true;
        this.isUploadSectionEnabled = true;
    }
    resetState(){
        this.isFile = false;
        this.isUpload = false;
    }
    onItemSelect(item:any){
        console.log(item);
        console.log(this.selectedItems);
    }
    OnItemDeSelect(item:any){
        console.log(item);
        console.log(this.selectedItems);
    }
    onSelectAll(items: any){
        console.log(items);
    }
    onDeSelectAll(items: any){
        console.log(items);
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

    upload() {
        console.log('in response event upload called HNS here');
        this.currentFileUpload = this.selectedFiles.item(0)
        this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
            console.log('in response event')
            console.log(event);
            this.visible = true;
            this.status = event.status;
            var message = event.message;
            var input = message.split(",");
            console.log('input.length');
            console.log(input.length);
            var i = 0;
            var str = "";
            while (i < input.length) {
                console.log(input[i]);
                str += "\n"+input[i];
                i++;
                
            }
            console.log('string value');
            console.log(str);

            this.responseLabel = str;
            this.btnDisable = true;
            this.fileupload.nativeElement.value = "";
        },
            (err) => {
                console.log('in error');
                this.btnDisable = true;
                this.fileupload.nativeElement.value = "";
            });



    }
    selectFile(event) {
        this.selectedFiles = event.target.files;
		console.log("printing the file names");
		console.log(event.target.value);
		this.btnDisable = false;
        this.responseLabel = "";
    }

    onSubmit() {
        console.log('onSubmit Calling Here...');
        
        this.responseLabel="";

        if(this.pccform.valid){

        console.log('entered into the submit function', this.pccform.value);
        this.addHNSModel.pcc=this.pccform.value.pcc;
        this.addHNSModel.uploaded_by=this.pccform.value.UploadedBy;

        // this.UploadHNSService.create(this.addHNSModel).subscribe(event => {
        //     this.visible = true;
        //     this.status = event.status;
        //     this.responseLabel = event.message;
        // },
        //     (err) => {
        //         console.log('in error');
        //     });

        //     this.pccform.reset();
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
