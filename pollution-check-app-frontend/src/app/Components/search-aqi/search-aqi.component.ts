import { Component, OnInit } from '@angular/core'
import { IqairService } from 'src/app/Services/iqair.service'

@Component({
  selector: 'app-search-aqi',
  templateUrl: './search-aqi.component.html',
  styleUrls: ['./search-aqi.component.css'],
})
export class SearchAqiComponent implements OnInit {
  temp_countries: any
  temp_states: any
  temp_cities: any
  temp_neareastData: any
  pollution: any
  aqius!: number
  aqius_nearest!:number
  selectedCountry: string = ''
  selectedState: string = ''
  selectedCity: string = ''
  selectedStation = ''
  constructor(private iqair: IqairService) {}

  ngOnInit(): void {
    this.iqair.getCountries().subscribe((country) => {
      this.temp_countries = country.data
    })
    this.iqair.getNearestAqi().subscribe((data)=>{
      this.temp_neareastData=data;
      this.aqius_nearest = this.temp_neareastData.data.current.pollution.aqius
    })
  }
  onSelectCountry(country: any) {
    this.iqair.getStates(String(country.value)).subscribe((state) => {
      this.temp_states = state.data
    })
    console.log(this.temp_states)
  }
  onSelectState(state: any) {
    this.iqair
      .getCities(this.selectedCountry, String(state.value))
      .subscribe((city) => {
        this.temp_cities = city.data
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
