import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeRoomComponent } from './type-room.component';

describe('TypeRoomComponent', () => {
  let component: TypeRoomComponent;
  let fixture: ComponentFixture<TypeRoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TypeRoomComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TypeRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
