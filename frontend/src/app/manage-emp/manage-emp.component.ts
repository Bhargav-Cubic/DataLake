import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { Location } from '@angular/common';
import { EmpHierService } from './manage-emp.service';
import { EmployeeHierarchyModel } from './manage-emp-model';
import { Response } from '@angular/http/src/static_response';
import { DISABLED } from '@angular/forms/src/model';


@Component({
    selector: 'app-add-pcc',
    templateUrl: './manage-emp.component.html',
    styleUrls: ['./manage-emp.component.scss']
})
export class ManageEmpComponent implements OnInit {


    pccform: FormGroup;
    form: FormGroup;
    empHierModel = new EmployeeHierarchyModel;
    submitted = false;
    status: boolean = true;
    visible: boolean = false;
    dropdownList = [];
    selectedItems = [];
    dropdownSettings = {};
    selected: number = 0;
    responseLabel: string = "";
    StringValue : string = "";
    constructor(private formBuilder: FormBuilder, private empHierService: EmpHierService) { }

    ngOnInit() {
        this.pccform = this.formBuilder.group({
            corpId: [null, Validators.required],
            UploadedBy: [null, Validators.required],
            level : ['BU LEVELS'],
            fluid : ['Yes'],
            
        });
        this.dropdownList = [
            { id: 0, name: 'Yes' },
            { id: 1, name: 'No' },
          ];
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
    selectOption(){
        console.log('Calling here')
    }

    onSubmit() {
        this.responseLabel="";

        if(this.pccform.valid){
            // this.expFile();
            console.log('entered into the submit function', this.pccform.value);
            this.empHierModel.corpId=this.pccform.value.corpId;
            this.empHierModel.userName=this.pccform.value.UploadedBy;
            this.empHierModel.historyFields=this.pccform.value.level;
            this.empHierModel.fluid=this.pccform.value.fluid
            console.log('Values we have to send === ', this.empHierModel);
            

            // empHierarchy
            this.empHierService.create(this.empHierModel).subscribe(event => {
                console.log("event response",event);
                console.log("event response",event);
                if(event.status == true){
                    this.visible = true;
                    this.status = true;
                    this.responseLabel = event.message;
                }
                else if(event.status == false){
                    this.status = false;
                    this.responseLabel = event.message;
                }
                else{
                    this.status = false;
                    this.responseLabel = 'Backend Error. Please contact System Admin.';
                }
                
                this.pccform.value.level = 'BU LEVELS';
                this.pccform.value.fluid = 'Yes';
            },

                (err) => {
                    console.log('in error',err);
                this.status = false;
                    
                    this.responseLabel = "You have not sucessfully uploaded the file.";
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

    saveTextAsFile (data, filename){

        if(!data) {
            console.error('Console.save: No data')
            return;
        }

        if(!filename) filename = 'console.json'

        var blob = new Blob([data], {type: 'text/plain'}),
            e    = document.createEvent('MouseEvents'),
            a    = document.createElement('a')
// FOR IE:

        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
            window.navigator.msSaveOrOpenBlob(blob, filename);
        }
        else{
            var e = document.createEvent('MouseEvents'),
                a = document.createElement('a');

            a.download = filename;
            console.log(filename);
            a.href = window.URL.createObjectURL(blob);
            a.dataset.downloadurl = ['text/plain', a.download, a.href].join(':');
            e.initEvent('click', true, false);
            console.log("e === ",e);
            // , window,0, 0, 0, 0, 0, false, false, false, false, 0, null);
            a.dispatchEvent(e);
        }                                               
    }

    onFileChange(event) {
        console.log("Here is the file ",event)
        let reader = new FileReader();
        if(event.target.files && event.target.files.length > 0) {
          let file = event.target.files[0];
          console.log("Here is the file ",file);
          reader.readAsDataURL(file);
          this.empHierService.create(file).subscribe(event => {
            this.visible = true;
            this.status = event.status;
            this.responseLabel = event.message;
        },
            (err) => {
                console.log('in error');
            });
        //   reader.onload = () => {
        //     this.form.get('avatar').setValue({
        //       filename: file.name,
        //       filetype: file.type,
        //       value: reader.result.split(',')[1]
        //     })
        //   };
        }
      }


    expFile() {
        // this.addPCCModel.pcc=this.pccform.value.pcc;
        // this.addPCCModel.uploaded_by=this.pccform.value.UploadedBy;
        var fileText = this.pccform.value.corpId + ' | ' + this.pccform.value.fluid + ' | ' + this.pccform.value.level + ' | ' + this.pccform.value.UploadedBy ;//"I am the first part of the info being emailed.\r\nI am the second part.\r\nI am the third part.";
        var fileName = "empHierarchy.txt"
        this.saveTextAsFile(fileText, fileName);
    }
}
