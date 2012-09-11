package kelgal.empleos

import org.springframework.dao.DataIntegrityViolationException

class CiudadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ciudadInstanceList: Ciudad.list(params), ciudadInstanceTotal: Ciudad.count()]
    }

    def create() {
        [ciudadInstance: new Ciudad(params)]
    }

    def save() {
        def ciudadInstance = new Ciudad(params)
        if (!ciudadInstance.save(flush: true)) {
            render(view: "create", model: [ciudadInstance: ciudadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudadInstance.id])
        redirect(action: "show", id: ciudadInstance.id)
    }

    def show(Long id) {
        def ciudadInstance = Ciudad.get(id)
        if (!ciudadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), id])
            redirect(action: "list")
            return
        }

        [ciudadInstance: ciudadInstance]
    }

    def edit(Long id) {
        def ciudadInstance = Ciudad.get(id)
        if (!ciudadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), id])
            redirect(action: "list")
            return
        }

        [ciudadInstance: ciudadInstance]
    }

    def update(Long id, Long version) {
        def ciudadInstance = Ciudad.get(id)
        if (!ciudadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ciudadInstance.version > version) {
                ciudadInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ciudad.label', default: 'Ciudad')] as Object[],
                          "Another user has updated this Ciudad while you were editing")
                render(view: "edit", model: [ciudadInstance: ciudadInstance])
                return
            }
        }

        ciudadInstance.properties = params

        if (!ciudadInstance.save(flush: true)) {
            render(view: "edit", model: [ciudadInstance: ciudadInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudadInstance.id])
        redirect(action: "show", id: ciudadInstance.id)
    }

    def delete(Long id) {
        def ciudadInstance = Ciudad.get(id)
        if (!ciudadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), id])
            redirect(action: "list")
            return
        }

        try {
            ciudadInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), id])
            redirect(action: "show", id: id)
        }
    }
}
