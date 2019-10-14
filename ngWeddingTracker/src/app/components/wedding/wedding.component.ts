import { WeddingService } from './../../services/wedding.service';
import { Component, OnInit } from '@angular/core';
import { Wedding } from 'src/app/models/wedding';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-wedding',
  templateUrl: './wedding.component.html',
  styleUrls: ['./wedding.component.css']
})
export class WeddingComponent implements OnInit {
  title = 'Weddings';
  editWedding = null;
  selected = null;

  weddings: Wedding[] = [];

  newWedding = new Wedding();

  constructor(private weddingService: WeddingService) {}

  ngOnInit() {
    this.reloadWeddings();
  }

  setEditWedding() {
    this.editWedding = Object.assign({}, this.selected);
  }

  cancelEditWedding() {
    this.editWedding = null;
  }

  showTotalWeddings(): number {
    return this.weddings.length;
  }

  displayWedding(wedding: Wedding) {
    this.selected = wedding;
  }

  displayTable() {
    this.selected = null;
  }

  addWedding(form: NgForm) {
    this.newWedding = new Wedding();
    this.newWedding.bookingDate = form.value.bookingDate;
    this.newWedding.celebrationDate = form.value.celebrationDate;
    this.newWedding.totalCost = form.value.totalCost;
    this.newWedding.upLighting = form.value.upLighting;
    this.newWedding.notes = form.value.notes;
    this.weddingService.create(this.newWedding).subscribe(
      () => {
        this.reloadWeddings();
      },
      err => {
        console.error('todoListComponent - addTodo()');
        console.error(err);
      }
    );
    form.reset();
  }

  updateWedding(id: number, editedWedding: Wedding) {
    this.weddingService.update(id, editedWedding).subscribe(
      () => {
        this.reloadWeddings();
      },
      err => {
        console.error('wedding.component - updateWedding()');
        console.error(err);
      }
    );
    this.editWedding = null;
    this.selected = null;
  }

  deleteWedding(id: number) {
    this.weddingService.destroy(id).subscribe(
      () => {
        this.reloadWeddings();
      },
      err => {
        console.error('WeddingComponent - deleteWedding()');
        console.error(err);
      }
    );
    this.reloadWeddings();
  }

  reloadWeddings() {
    this.weddingService.index().subscribe(
      lifeIsGood => {
        this.weddings = lifeIsGood;
      },
      lifeIsBad => {
        console.error('Error in WeddingComponent.reloadWeddings()');
        console.error(lifeIsBad);
      }
    );
  }
}
