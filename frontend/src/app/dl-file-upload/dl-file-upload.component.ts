import { Component, OnInit, Input, QueryList, ViewChildren, ElementRef } from '@angular/core';
import { PipeTransform, Pipe } from '@angular/core';
import { forEach } from '@angular/router/src/utils/collection';
import { Injectable } from '@angular/core';
import { ViewChild } from '@angular/core';
import { NgIf } from '@angular/common/src/directives/ng_if';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { BsDropdownModule } from 'ngx-bootstrap';
import { DlFileUploadService } from './dl-file-upload.service';
import { DlFileUploadModel } from './dl-file-upload-model';
import { DateFormatter } from 'ngx-bootstrap/datepicker/date-formatter';
import { DatePipe } from '@angular/common';
import { AuthService } from '../auth/auth.service';
import { format } from 'url';










@Component({
  selector: 'app-dl-file-upload',
  templateUrl: './dl-file-upload.component.html',
  styleUrls: ['./dl-file-upload.component.scss']
})
export class DlFileUploadComponent implements OnInit {




  dlFileUploadModel = new DlFileUploadModel;
  fileformat = new Array("Comma Delimited( , )", "Pipe Delimited( | )", "Ctrl+A Delimited", "Tab Delimited(\\t)");
  filefrequency = new Array("One Time", "Daily", "Weekly", "Monthly", "Yearly");
  purgefrequency = new Array("1 Week", "2 Weeks", "3 Weeks", "4 Weeks", "5 Weeks", "6 Weeks", "7 Weeks", "8 Weeks");
  filecontenttype = new Array("PII", "PCI", "NONE");
  typeoffile = new Array("Csv", "Text");

  fileupload_flag: boolean;
  mftupload_flag: boolean;
  clientspecificdata: boolean;
  PCI_Message: String = "";
  filetype_flag: boolean = false;
  isTnc: String;
  date = new Date();
  dt = this.date.getDate;


  dlfileupload_status: boolean = false;
  dlfileupload_response_message: String = '';

  clientdetails_check: boolean = true;






  selectedFiles: FileList
  currentFileUpload: File
  status: boolean = true;
  visible: boolean = false;
  btnDisable: boolean = true;
  responseLabel: string = ""
  @ViewChild('fileUpload')
  fileupload: any;
  fileUploaded: boolean = false;


  testfilename: String;
  testfilepath: String = "";
  testfilepath_flag: boolean = false;
  fileformat_flag: boolean = false;
  tnc_checked: boolean = true;
  tnc_touched: boolean = false;


  pipe = new DatePipe('en-US');
  now = Date.now();
  formattedDate = this.pipe.transform(this.now,'yyyy-MM-dd');

  
  











  dlfileupload: FormGroup;


  constructor(private formBuilder: FormBuilder, private dlFileUploadService: DlFileUploadService, private authService: AuthService) { }

  ngOnInit() {

    this.fileupload_flag = false;
    this.mftupload_flag = false;
    this.clientspecificdata = false;


    this.dlfileupload = this.formBuilder.group({
      ClientSpecificData: [null, Validators.required],
      ClientDetails: '',
      PCIData: [null, Validators.required],
      FileDescription: '',
      TypeOfFile: ['', Validators.required],
      FileFormat: '',
      FileUpload: [null, Validators.required],
      fileuploaded: '',
      MFTFilePrefix: '',
      MFTFileFrequency: '',
      PurgeFrequency: '',
      tnc: ''

    });




    this.dlfileupload.get('ClientSpecificData').valueChanges.subscribe(value => this.setNotificationClientDetails(value));

  }


  setNotificationClientDetails(notify: String) {
    const clientdetails = this.dlfileupload.get('ClientDetails');
    console.log("i am inside notification method");
    if (notify === 'Yes') {
      clientdetails.setValidators(Validators.required);
      this.clientdetails_check = false;
      console.log("I am inside notify method");
    } else {
      clientdetails.clearValidators();
      this.clientdetails_check = true;
    }
    clientdetails.updateValueAndValidity();
  }


  clientSpecificDataYes() {
    this.clientspecificdata = true;
    //this.dlfileupload.get('ClientDetails').setValidators(Validators.required);
  }
  clientSpecificDataNo() {
    this.dlfileupload.controls['ClientDetails'].setValue('');
    //this.dlfileupload.get('ClientDetails').clearValidators();
    this.clientspecificdata = false;


  }

  IsPCIDataYes() {
    this.PCI_Message = "";
    this.PCI_Message = "We cannot proceed with PCI type files ";
    this.filetype_flag = true;

  }


  IsPCIDataNo() {
    this.PCI_Message = "";
    this.filetype_flag = false;
  }






  onChangeTypeOfFile(fileformattype) {
    if (fileformattype == 'Text') {
      this.fileformat_flag = true;
    } else {
      this.dlfileupload.controls['FileFormat'].setValue('');
      this.fileformat_flag = false;
    }

  }



  onChangeMFTFileFrequency(frequency) {
    console.log(frequency);
    this.fileupload_flag = false;
    if (this.mftupload_flag) {
      this.testfilepath_flag = true;
      this.testfilepath = "/user/"+this.authService.user+"/custom_data/"+this.formattedDate+"/";
    }



  }

  dlfileUpload() {
    this.dlfileupload.controls['MFTFilePrefix'].setValue('');
    this.dlfileupload.controls['MFTFileFrequency'].setValue('');
    this.mftupload_flag = false;
    this.fileupload_flag = true;
    this.testfilepath_flag = false;
    console.log("this.fileupload_flag" + this.fileupload_flag);

  }

  mftUpload() {
    this.fileupload_flag = false;
    this.mftupload_flag = true;
    this.testfilepath_flag = false;

    console.log("this.mftupload_flag" + this.mftupload_flag);
  }

  tncCheck() {
    console.log("checked");
    var element = <HTMLInputElement>document.getElementById("tnc");
    if (element.checked) {
      this.isTnc = "Y";
      this.tnc_touched = true;
      this.tnc_checked = true;

    } else {
      this.isTnc = "N";
      this.tnc_touched = false;
      this.tnc_checked = false;
    }



  }




  onSubmit() {

    


    console.log("We are in submit method")



    if (this.tnc_touched) {
      this.tnc_checked = true;
      if (this.dlfileupload.valid) {

        this.dlfileupload_response_message = "";

        if (this.fileupload_flag) {
          this.upload();
        } else {
          this.uploadstaustest(this.dlfileupload_status);
        }

        /*
                console.log("this.dlfileupload_status is " + this.dlfileupload_status)
                console.log("this.mftupload_flag is " + this.mftupload_flag)
        
                if (this.dlfileupload_status || this.mftupload_flag) {
        
                    console.log("we are inside check ====="+this.dlfileupload_status);
                  if (typeof this.dlfileupload.value.ClientDetails !== 'undefined') {
                    this.dlFileUploadModel.clientdetails = this.dlfileupload.value.ClientDetails;
                  } else {
                    this.dlFileUploadModel.clientdetails = null;
                  }
                  this.dlFileUploadModel.file_description = this.dlfileupload.value.FileDescription;
                  this.dlFileUploadModel.typeoffile = this.dlfileupload.value.TypeOfFile;
                  this.dlFileUploadModel.fileformat = this.dlfileupload.value.FileFormat;
                  if (this.fileUploaded) {
                    this.dlFileUploadModel.isfileupload = "Y";
                  } else {
                    this.dlFileUploadModel.isfileupload = "N";
                  }
                  if (typeof this.dlfileupload.value.MFTFileFrequency !== 'undefined') {
                    this.dlFileUploadModel.mftfilefrequency = this.dlfileupload.value.MFTFileFrequency;
                  } else {
                    this.dlFileUploadModel.mftfilefrequency = null;
                  }
                  if (typeof this.dlfileupload.value.MFTFilePrefix !== 'undefined') {
                    this.dlFileUploadModel.mftfileprefix = this.dlfileupload.value.MFTFilePrefix;
                  } else {
                    this.dlFileUploadModel.mftfileprefix = null;
                  }
        
                  if (typeof this.dlfileupload.value.PurgeFrequency !== 'undefined') {
                    this.dlFileUploadModel.purgefrequency = this.dlfileupload.value.PurgeFrequency;
                  } else {
                    this.dlFileUploadModel.purgefrequency = null;
                  }
                  if (typeof this.testfilename !== 'undefined') {
                    this.dlFileUploadModel.filename = this.testfilename;
                  } else {
                    this.dlFileUploadModel.filename = '';
                  }
                  if (typeof this.testfilename !== 'undefined') {
                    this.dlFileUploadModel.filepath = "/user/svc_aegbtdi/custom_data/" + this.testfilename;
                  } else {
                    this.dlFileUploadModel.filepath = "/user/svc_aegbtdi/custom_data/";
                  }
                  this.dlFileUploadModel.tnc = this.isTnc;
                  this.dlFileUploadModel.uploaded_by = this.authService.user;
        
        
                  console.log("we are in dl form data");
        
                  console.log("DlFileUploadModel" + this.dlFileUploadModel);
        
        
        
                  //api call
                  this.dlFileUploadService.create(this.dlFileUploadModel).subscribe(event => {
                    this.visible = true;
                    this.status = event.status;
                    this.responseLabel = event.message;
                  },
                    (err) => {
                      console.log('in error');
                    });
        
        
        
                } else {
        
                  this.dlfileupload.reset();
        
                }
        
                this.dlfileupload.reset();
                */
      } else {
        this.validateAllFormFields(this.dlfileupload);
      }
    } else {
      this.validateAllFormFields(this.dlfileupload);
      this.tnc_checked = false;
    }


  }



  selectFile(event) {
    this.selectedFiles = event.target.files;
    let file = event.target.files[0];
    this.testfilename = file.name;
    this.fileUploaded = true;
    this.btnDisable = false;
    this.responseLabel = "";
    if (this.fileupload_flag) {
      this.testfilepath_flag = true;
      this.testfilepath = "/user/"+this.authService.user+"/custom_data/"+this.formattedDate+"/"+ this.testfilename;
      console.log("this.testfilepath" + this.testfilepath);
    }

  }
  upload() {

    this.currentFileUpload = this.selectedFiles.item(0)
    this.dlFileUploadService.pushFileToDLStorage(this.currentFileUpload).subscribe(event => {
      console.log('in response event')
      console.log(event);
      this.dlfileupload_status = event.status;
      console.log("this.dlfileupload_status inside upload" + this.dlfileupload_status);
      //this.responseLabel = str;
      this.dlfileupload_response_message = event.message;
      this.btnDisable = true;
      this.fileupload.nativeElement.value = "";
      this.uploadstaustest(this.dlfileupload_status);

    },

      (err) => {
        console.log('in error');
        this.btnDisable = true;
        this.fileupload.nativeElement.value = "";
      });





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

  isFieldValid(field: string) {
    return !this.dlfileupload.get(field).valid && this.dlfileupload.get(field).touched;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }

  uploadstaustest(dlstatus: boolean) {
    if (this.dlfileupload_status || this.mftupload_flag) {

      console.log("we are inside check =====" + this.dlfileupload_status);
      if (typeof this.dlfileupload.value.ClientDetails !== 'undefined') {
        this.dlFileUploadModel.clientdetails = this.dlfileupload.value.ClientDetails;
      } else {
        this.dlFileUploadModel.clientdetails = null;
      }
      this.dlFileUploadModel.file_description = this.dlfileupload.value.FileDescription;
      this.dlFileUploadModel.typeoffile = this.dlfileupload.value.TypeOfFile;
      this.dlFileUploadModel.fileformat = this.dlfileupload.value.FileFormat;
      if (this.fileUploaded) {
        this.dlFileUploadModel.isfileupload = "Y";
      } else {
        this.dlFileUploadModel.isfileupload = "N";
      }
      if (typeof this.dlfileupload.value.MFTFileFrequency !== 'undefined') {
        this.dlFileUploadModel.mftfilefrequency = this.dlfileupload.value.MFTFileFrequency;
      } else {
        this.dlFileUploadModel.mftfilefrequency = null;
      }
      if (typeof this.dlfileupload.value.MFTFilePrefix !== 'undefined') {
        this.dlFileUploadModel.mftfileprefix = this.dlfileupload.value.MFTFilePrefix;
      } else {
        this.dlFileUploadModel.mftfileprefix = null;
      }

      if (typeof this.dlfileupload.value.PurgeFrequency !== 'undefined') {
        this.dlFileUploadModel.purgefrequency = this.dlfileupload.value.PurgeFrequency;
      } else {
        this.dlFileUploadModel.purgefrequency = null;
      }
      if (typeof this.testfilename !== 'undefined') {
        this.dlFileUploadModel.filename = this.testfilename;
      } else {
        this.dlFileUploadModel.filename = '';
      }
      if (typeof this.testfilename !== 'undefined') {
        this.dlFileUploadModel.filepath = "/user/"+this.authService.user+"/custom_data/" +this.formattedDate+"/"+ this.testfilename;
      } else {
        this.dlFileUploadModel.filepath = "/user/"+this.authService.user+"/custom_data/"+this.formattedDate;
      }
      this.dlFileUploadModel.tnc = this.isTnc;
      this.dlFileUploadModel.uploaded_by = this.authService.user;


      console.log("we are in dl form data");

      console.log("DlFileUploadModel" + this.dlFileUploadModel);



      //api call
      this.dlFileUploadService.create(this.dlFileUploadModel).subscribe(event => {
        this.visible = true;
        this.status = event.status;
        this.responseLabel = event.message;
      },
        (err) => {
          console.log('in error');
        });



    } else {

      this.dlfileupload.reset();

    }

    this.dlfileupload.reset();
  }




}


