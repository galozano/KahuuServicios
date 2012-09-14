package kelgal.empleo.auto

import kelgal.empleos.Referencia;

import org.springframework.dao.DataIntegrityViolationException


class ReferenciaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [referenciaInstanceList: Referencia.list(params), referenciaInstanceTotal: Referencia.count()]
    }

    def create() {
        [referenciaInstance: new Referencia(params)]
    }

    def save() {
        def referenciaInstance = new Referencia(params)
        if (!referenciaInstance.save(flush: true)) {
            render(view: "create", model: [referenciaInstance: referenciaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'referencia.label', default: 'Referencia'), referenciaInstance.id])
        redirect(action: "show", id: referenciaInstance.id)
    }

    def show(Long id) {
        def referenciaInstance = Referencia.get(id)
        if (!referenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), id])
            redirect(action: "list")
            return
        }

        [referenciaInstance: referenciaInstance]
    }

    def edit(Long id) {
        def referenciaInstance = Referencia.get(id)
        if (!referenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), id])
            redirect(action: "list")
            return
        }

        [referenciaInstance: referenciaInstance]
    }

    def update(Long id, Long version) {
        def referenciaInstance = Referencia.get(id)
        if (!referenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (referenciaInstance.version > version) {
                referenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'referencia.label', default: 'Referencia')] as Object[],
                          "Another user has updated this Referencia while you were editing")
                render(view: "edit", model: [referenciaInstance: referenciaInstance])
                return
            }
        }

        referenciaInstance.properties = params

        if (!referenciaInstance.save(flush: true)) {
            render(view: "edit", model: [referenciaInstance: referenciaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'referencia.label', default: 'Referencia'), referenciaInstance.id])
        redirect(action: "show", id: referenciaInstance.id)
    }

    def delete(Long id) {
        def referenciaInstance = Referencia.get(id)
        if (!referenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), id])
            redirect(action: "list")
            return
        }

        try {
            referenciaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'referencia.label', default: 'Referencia'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'referencia.label', default: 'Referencia'), id])
            redirect(action: "show", id: id)
        }
    }
}
