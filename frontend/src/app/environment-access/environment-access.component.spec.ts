import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnvironmentAccessComponent } from './environment-access.component';

describe('EnvironmentAccessComponent', () => {
  let component: EnvironmentAccessComponent;
  let fixture: ComponentFixture<EnvironmentAccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnvironmentAccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnvironmentAccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
