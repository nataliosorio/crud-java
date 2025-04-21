import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationRoomComponent } from './reservation-room.component';

describe('ReservationRoomComponent', () => {
  let component: ReservationRoomComponent;
  let fixture: ComponentFixture<ReservationRoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReservationRoomComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
