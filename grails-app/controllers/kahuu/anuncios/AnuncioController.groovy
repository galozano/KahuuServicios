package kahuu.anuncios

import org.springframework.dao.DataIntegrityViolationException

class AnuncioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [anuncioInstanceList: Anuncio.list(params), anuncioInstanceTotal: Anuncio.count()]
    }

    def create() {
        [anuncioInstance: new Anuncio(params)]
    }

    def save() {
        def anuncioInstance = new Anuncio(params)
        if (!anuncioInstance.save(flush: true)) {
            render(view: "create", model: [anuncioInstance: anuncioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), anuncioInstance.id])
        redirect(action: "show", id: anuncioInstance.id)
    }

    def show(Long id) {
        def anuncioInstance = Anuncio.get(id)
        if (!anuncioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), id])
            redirect(action: "list")
            return
        }

        [anuncioInstance: anuncioInstance]
    }

    def edit(Long id) {
        def anuncioInstance = Anuncio.get(id)
        if (!anuncioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), id])
            redirect(action: "list")
            return
        }

        [anuncioInstance: anuncioInstance]
    }

    def update(Long id, Long version) {
        def anuncioInstance = Anuncio.get(id)
        if (!anuncioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (anuncioInstance.version > version) {
                anuncioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'anuncio.label', default: 'Anuncio')] as Object[],
                          "Another user has updated this Anuncio while you were editing")
                render(view: "edit", model: [anuncioInstance: anuncioInstance])
                return
            }
        }

        anuncioInstance.properties = params

        if (!anuncioInstance.save(flush: true)) {
            render(view: "edit", model: [anuncioInstance: anuncioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), anuncioInstance.id])
        redirect(action: "show", id: anuncioInstance.id)
    }

    def delete(Long id) {
        def anuncioInstance = Anuncio.get(id)
        if (!anuncioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), id])
            redirect(action: "list")
            return
        }

        try {
            anuncioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'anuncio.label', default: 'Anuncio'), id])
            redirect(action: "show", id: id)
        }
    }
}
