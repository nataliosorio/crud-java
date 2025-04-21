import { Component, OnInit } from '@angular/core';
import { City, ServiceCityService } from '../service-city.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-city',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './city.component.html',
  styleUrl: './city.component.css'
})
export class CityComponent implements OnInit{

  cities: City[] = [];

  constructor(private cityService: ServiceCityService) {}

  ngOnInit(): void {
    this.cityService.getCities().subscribe(data => {
      this.cities = data;
      console.log(this.cities);
    });
  }
}
