import { Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { CityComponent } from './city/city.component';
import { TypeRoomComponent } from './type-room/type-room.component';
import { RoleComponent } from './role/role.component';
import { DocumentTypeComponent } from './document-type/document-type.component';
import { HotelComponent } from './hotel/hotel.component';
import { EmployeeComponent } from './employee/employee.component';
import { CustomerComponent } from './customer/customer.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { ReservationComponent } from './reservation/reservation.component';
import { RoomComponent } from './room/room.component';

export const routes: Routes = [
    // {path: "", component: MenuComponent},
    { path: 'crudCity', component: CityComponent },
    { path: 'crudTypeRoom', component: TypeRoomComponent },
    { path: 'crudRol', component: RoleComponent },
    { path: 'crudDocumentType', component: DocumentTypeComponent },
    { path: 'Hotel', component: HotelComponent },
    { path: 'Employee', component: EmployeeComponent },
    { path: 'Customer', component: CustomerComponent },
    { path: 'Invoice', component: InvoiceComponent },
    { path: 'Reservation', component: ReservationComponent },
    { path: 'Room', component: RoomComponent },



    
    




    // {path: "/crudCity", component: CityComponent},

    // { path: 'hoteles', component: HotelesComponent },
    // { path: 'habitaciones', component: HabitacionesComponent },
    // { path: 'reservas', component: ReservasComponent },
    // { path: 'huespedes', component: HuespedesComponent },
    // { path: 'configuracion', component: ConfiguracionComponent },
    // { path: '**', redirectTo: 'hoteles' }

    // { path: 'loading', component: EmpresaIndexComponent },
    // { path: 'empresas/crear', component: EmpresaFormularioComponent }
];
