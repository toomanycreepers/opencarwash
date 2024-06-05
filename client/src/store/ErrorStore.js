import { makeAutoObservable } from "mobx"

class ErrorStore {
    errors = []

    constructor() {
        makeAutoObservable(this)
    }

    addError(error) {
        this.errors.push(error)
    }

    removeError(error) {
        this.errors = this.errors.filter(e => e !== error)
    }
}

export default new ErrorStore()
