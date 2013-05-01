package kahuu.general.auto

import kahuu.general.Categorias;

import org.springframework.dao.DataIntegrityViolationException

class CategoriasController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [categoriasInstanceList: Categorias.list(params), categoriasInstanceTotal: Categorias.count()]
    }

    def create() {
        [categoriasInstance: new Categorias(params)]
    }

    def save() {
        def categoriasInstance = new Categorias(params)
        if (!categoriasInstance.save(flush: true)) {
            render(view: "create", model: [categoriasInstance: categoriasInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'categorias.label', default: 'Categorias'), categoriasInstance.id])
        redirect(action: "show", id: categoriasInstance.id)
    }

    def show(Long id) {
        def categoriasInstance = Categorias.get(id)
        if (!categoriasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorias.label', default: 'Categorias'), id])
            redirect(action: "list")
            return
        }

        [categoriasInstance: categoriasInstance]
    }

    def edit(Long id) {
        def categoriasInstance = Categorias.get(id)
        if (!categoriasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorias.label', default: 'Categorias'), id])
            redirect(action: "list")
            return
        }

        [categoriasInstance: categoriasInstance]
    }

    def update(Long id, Long version) {
        def categoriasInstance = Categorias.get(id)
        if (!categoriasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorias.label', default: 'Categorias'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (categoriasInstance.version > version) {
                categoriasInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'categorias.label', default: 'Categorias')] as Object[],
                          "Another user has updated this Categorias while you were editing")
                render(view: "edit", model: [categoriasInstance: categoriasInstance])
                return
            }
        }

        categoriasInstance.properties = params

        if (!categoriasInstance.save(flush: true)) {
            render(view: "edit", model: [categoriasInstance: categoriasInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'categorias.label', default: 'Categorias'), categoriasInstance.id])
        redirect(action: "show", id: categoriasInstance.id)
    }

    def delete(Long id) {
        def categoriasInstance = Categorias.get(id)
        if (!categoriasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorias.label', default: 'Categorias'), id])
            redirect(action: "list")
            return
        }

        try {
            categoriasInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'categorias.label', default: 'Categorias'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'categorias.label', default: 'Categorias'), id])
            redirect(action: "show", id: id)
        }
    }
}
