import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { MatButtonModule } from '@angular/material/button'
// import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatSelectModule } from '@angular/material/select'
// import { MatRadioModule } from '@angular/material/radio';
import { MatIconModule } from '@angular/material/icon'
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatSidenavModule } from '@angular/material/sidenav'
import { MatNativeDateModule } from '@angular/material/core'
import { MatCardModule } from '@angular/material/card'
// import {MatRippleModule} from '@angular/material/core';
// import {MatChipsModule} from '@angular/material/chips';
import { MatSnackBarModule } from '@angular/material/snack-bar'
import { MatMenuModule } from '@angular/material/menu'
// import {MatProgressBarModule} from '@angular/material/progress-bar';
const modules = [
  MatButtonModule,
  // MatDatepickerModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  // MatRadioModule,
  MatIconModule,
  MatToolbarModule,
  MatSidenavModule,
  MatNativeDateModule,
  MatCardModule,
  // MatRippleModule,
  // MatChipsModule,
  MatSnackBarModule,
  // MatProgressBarModule
  MatMenuModule,
]

@NgModule({
  declarations: [],
  imports: [CommonModule],
  exports: [modules],
})
export class NgMaterialModule {}
