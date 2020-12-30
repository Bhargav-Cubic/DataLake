import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DlFileUploadComponent } from './dl-file-upload.component';

describe('DlFileUploadComponent', () => {
  let component: DlFileUploadComponent;
  let fixture: ComponentFixture<DlFileUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DlFileUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DlFileUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
