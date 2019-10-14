export class Wedding {
  id: number;
  bookingDate: Date;
  celebrationDate: Date;
  upLighting: number;
  totalCost: number;
  notes: string;

  constructor(
    id?: number,
    bookingDate?: Date,
    celebrationDate?: Date,
    upLighting?: number,
    totalCost?: number,
    notes?: string
  ) {
    this.id = id;
    this.bookingDate = bookingDate;
    this.celebrationDate = celebrationDate;
    this.upLighting = upLighting;
    this.totalCost = totalCost;
    this.notes = notes;
  }
}
