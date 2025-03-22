import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TogglecartService {
  static ToggleWatchList(cardId: number) {
    if (typeof localStorage !== 'undefined') {
      var watchList = JSON.parse(localStorage.getItem('watchList') || '[]');
    } 

    const index = watchList.indexOf(cardId);

    if (index === -1) {
      // Card ID is not in the watch list, so add it
      watchList.push(cardId);
    } else {
      // Card ID is in the watch list, so remove it
      watchList.splice(index, 1);
    }

    // Save the updated watch list back to local storage
    if (typeof localStorage !== 'undefined') {
      localStorage.setItem('watchList', JSON.stringify(watchList));
    }
  }
  constructor() { }
}
