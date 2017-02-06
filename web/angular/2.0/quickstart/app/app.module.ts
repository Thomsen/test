import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { AppComponent } from './app.component';

import { MaterialModule } from '@angular/material';

import { Logger } from './logger.service';

import 'hammerjs';

@NgModule({
  imports: [BrowserModule, MaterialModule.forRoot()],
  providers: [Logger],
  declarations: [AppComponent],
  exports: [AppComponent],
  bootstrap: [AppComponent]
})

export class AppModule { }
