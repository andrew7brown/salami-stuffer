import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoreMapContentComponent } from './core-map-content.component';

describe('CoreMapContentComponent', () => {
  let component: CoreMapContentComponent;
  let fixture: ComponentFixture<CoreMapContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoreMapContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoreMapContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
