export class CityData {
  constructor(
    public userEmail: string,
    public city: string,
    public State: string,
    public Country: string,
    public aqiUS: number,
    public healthStatus: string,
  ) {}
}
