package kahuu.general.auto

import kahuu.general.Certificado;

import org.springframework.dao.DataIntegrityViolationException



class CertificadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [certificadoInstanceList: Certificado.list(params), certificadoInstanceTotal: Certificado.count()]
    }

    def create() {
        [certificadoInstance: new Certificado(params)]
    }

    def save() {
        def certificadoInstance = new Certificado(params)
        if (!certificadoInstance.save(flush: true)) {
            render(view: "create", model: [certificadoInstance: certificadoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'certificado.label', default: 'Certificado'), certificadoInstance.id])
        redirect(action: "show", id: certificadoInstance.id)
    }

    def show(Long id) {
        def certificadoInstance = Certificado.get(id)
        if (!certificadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), id])
            redirect(action: "list")
            return
        }

        [certificadoInstance: certificadoInstance]
    }

    def edit(Long id) {
        def certificadoInstance = Certificado.get(id)
        if (!certificadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), id])
            redirect(action: "list")
            return
        }

        [certificadoInstance: certificadoInstance]
    }

    def update(Long id, Long version) {
        def certificadoInstance = Certificado.get(id)
        if (!certificadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (certificadoInstance.version > version) {
                certificadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'certificado.label', default: 'Certificado')] as Object[],
                          "Another user has updated this Certificado while you were editing")
                render(view: "edit", model: [certificadoInstance: certificadoInstance])
                return
            }
        }

        certificadoInstance.properties = params

        if (!certificadoInstance.save(flush: true)) {
            render(view: "edit", model: [certificadoInstance: certificadoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'certificado.label', default: 'Certificado'), certificadoInstance.id])
        redirect(action: "show", id: certificadoInstance.id)
    }

    def delete(Long id) {
        def certificadoInstance = Certificado.get(id)
        if (!certificadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), id])
            redirect(action: "list")
            return
        }

        try {
            certificadoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'certificado.label', default: 'Certificado'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'certificado.label', default: 'Certificado'), id])
            redirect(action: "show", id: id)
        }
    }
}
