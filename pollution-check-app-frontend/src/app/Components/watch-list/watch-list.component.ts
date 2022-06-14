import { Component, OnInit } from '@angular/core'
import { MatSnackBar } from '@angular/material/snack-bar'
import { CityData } from 'src/app/Models/city-data'
import { WatchlistService } from 'src/app/Services/watchlist.service'

@Component({
  selector: 'app-watch-list',
  templateUrl: './watch-list.component.html',
  styleUrls: ['./watch-list.component.css'],
})
export class WatchListComponent implements OnInit {
  data: CityData[] = []

  constructor(
    private watchlist: WatchlistService,
    private _snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    let email = localStorage.getItem('email')
    if (email == null) {
      email = 'nouser'
    }
    this.watchlist.getWatchListByEmail(email).subscribe((data) => {
      console.log(data)
      this.data = data
    })
  }

  deleteRecord(id: number | undefined) {
    console.log(id)
    this.watchlist.removeFromWatchList(id).subscribe((data) => {
      console.log(data)
      this.openSnackBar('Record deleted successfully', 'Ok')
      this.ngOnInit()
    })
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    })
  }
}
