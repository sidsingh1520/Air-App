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
  cardSubtitle = 'NEAREST CITY LIVE AQI INDEX'
  healthStatus = ''
  location = ''
  constructor(private iqair: IqairService) {}

  ngOnInit(): void {
    this.iqair.getCountries().subscribe((country) => {
      this.tempCountries = country.data
    })
    this.iqair.getNearestAqi().subscribe((data) => {
      this.tempNearestCity = data
      this.aqiusNearest = this.tempNearestCity.data.current.pollution.aqius
      this.changeHealthStatus(this.aqiusNearest)
      this.location =
        this.tempNearestCity.data.city +
        ', ' +
        this.tempNearestCity.data.state +
        ', ' +
        this.tempNearestCity.data.country
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
        this.tempNearestCity = data
        console.log(this.pollution)
        this.aqiusNearest = this.tempNearestCity.data.current.pollution.aqius
        this.changeHealthStatus(this.aqiusNearest)
      })
    this.cardSubtitle = 'LIVE AQI INDEX'

    this.location =
      this.selectedCity +
      ', ' +
      this.selectedState +
      ', ' +
      this.selectedCountry
  }

  changeHealthStatus(aqi: number) {
    if (aqi <= 50) {
      this.healthStatus = 'Healthy'
    } else if (aqi > 50 && aqi <= 100) {
      this.healthStatus = 'Moderate'
    } else if (aqi > 100 && aqi <= 150) {
      this.healthStatus = 'Sensitive'
    } else {
      this.healthStatus = 'Unhealthy'
    }
  }
}
