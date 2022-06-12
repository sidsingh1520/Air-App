import { Component, OnInit } from '@angular/core'
import { IqairService } from 'src/app/Services/iqair.service'

@Component({
  selector: 'app-search-aqi',
  templateUrl: './search-aqi.component.html',
  styleUrls: ['./search-aqi.component.css'],
})
export class SearchAqiComponent implements OnInit {
  tempCountries: any
  tempStates: any
  tempCities: any
  tempNearestCity: any
  pollution: any
  aqius!: number
  aqiusNearest!: number
  selectedCountry: string = ''
  selectedState: string = ''
  selectedCity: string = ''
  selectedStation = ''
  constructor(private iqair: IqairService) {}

  ngOnInit(): void {
    this.iqair.getCountries().subscribe((country) => {
      this.tempCountries = country.data
    })
    this.iqair.getNearestAqi().subscribe((data) => {
      this.tempNearestCity = data
      this.aqiusNearest = this.tempNearestCity.data.current.pollution.aqius
      console.log(this.aqiusNearest)
    })
  }
  onSelectCountry(country: any) {
    this.iqair.getStates(String(country.value)).subscribe((state) => {
      this.tempStates = state.data
    })
    console.log(this.tempStates)
  }
  onSelectState(state: any) {
    this.iqair
      .getCities(this.selectedCountry, String(state.value))
      .subscribe((city) => {
        this.tempCities = city.data
      })
  }
  // onSelectCity(city:any){
  //   this.iqair.getStations(this.selectedCountry,this.selectedState,String(city.value)).subscribe((station)=>{
  //     this.temp_stations=station.data;
  //   })
  // }
  onSubmit() {
    this.iqair
      .getDataStation(
        this.selectedCountry,
        this.selectedState,
        this.selectedCity,
      )
      .subscribe((data) => {
        this.pollution = data
        console.log(this.pollution)
        this.aqius = this.pollution.data.current.pollution.aqius
      })
  }
}
