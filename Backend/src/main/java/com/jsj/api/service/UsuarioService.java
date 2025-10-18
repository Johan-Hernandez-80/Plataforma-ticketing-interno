/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dao.RolDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.NotificacionDTO;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.exception.ContrasenaInvalidaException;
import com.jsj.api.exception.EmailInvalidoException;
import com.jsj.api.exception.IdInvalidaException;
import com.jsj.api.exception.ImmutableFieldException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.exception.RolInexistenteException;
import com.jsj.api.exception.UsuarioInexistenteException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioDTO, UsuarioDAO> {

    private final PasswordEncoder passwordEncoder;
    private final NotificacionDAO notificacionDAO;
    private final RolDAO rolDAO;

    public UsuarioService(PasswordEncoder passwordEncoder, NotificacionDAO notificacionDAO, RolDAO rolDAO, UsuarioDAO dao) {
        super(dao);
        this.passwordEncoder = passwordEncoder;
        this.notificacionDAO = notificacionDAO;
        this.rolDAO = rolDAO;
    }

    public Set<String> getPermissionsById(Long userId) {
        return dao.getPermissionsById(userId);
    }

    public Optional<Usuario> findById(long id) {
        return dao.findById(id);
    }

    public Usuario validateCredentials(String email, String password) {
        Usuario user = dao.findByEmailPersonal(email);
        if (user == null) {
            user = dao.findByEmailCorporativo(email);
        }
        if (user != null && passwordEncoder.matches(password, user.getContrasena())) {
            return user;
        }
        return null;
    }

    public List<NotificacionDTO> getNotificacionesById(Long idUsuario) {
        if (!dao.existsById(idUsuario)) {
            return null;
        }
        return notificacionDAO.getNotificacionesByIdUsuario(idUsuario);
    }

    public UsuarioDTO updateUsuario(Long idUsuario, UsuarioDTO dto) throws UsuarioInexistenteException, InsufficientSavingPermissionsException, RolInexistenteException, IdInvalidaException, EmailInvalidoException, ImmutableFieldException {
        validarIdUpdate(idUsuario, dto.getId());
        validarEmailsUpdate(dto.getEmailPersonal(), dto.getEmailCorporativo());
        validarRolIdUpdate(dto.getRolId());

        return dao.updateUsuario(idUsuario, dto);
    }

    public UsuarioDTO save(UsuarioDTO dto) throws IdInvalidaException, EmailInvalidoException, RolInexistenteException, InsufficientSavingPermissionsException, ContrasenaInvalidaException {
        validarIdSave(dto.getId());
        validarEmailsSave(dto.getEmailPersonal(), dto.getEmailCorporativo());
        validarRolIdSave(dto.getRolId());
        validarContrasenaSave(dto.getContrasena());

        return dao.save(dto);
    }

    private void validarRolIdUpdate(Long rolId) throws RolInexistenteException {
        if (rolId != null) {
            if (!rolDAO.existsById(rolId)) {
                throw new RolInexistenteException("El rol no existe");
            }
        }
    }

    private void validarIdUpdate(Long idUsu, Long idDto) throws IdInvalidaException {
        if (idDto != null) {
            if (idDto < 0) {
                throw new IdInvalidaException("La id no puede ser negativa");
            }
            if (existsById(idDto) && !Objects.equals(idUsu, idDto)) {
                throw new IdInvalidaException("La id no es única");
            }
        }
    }

    private void validarIdSave(Long id) throws IdInvalidaException {
        if (id != null) {
            if (id < 0) {
                throw new IdInvalidaException("La id no puede ser negativa");
            }
            if (existsById(id)) {
                throw new IdInvalidaException("La id no es única");
            }
        }
    }

    private void validarEmailsUpdate(String emailPersonal, String emailCorporativo) throws EmailInvalidoException {
        if (emailPersonal != null) {
            if (dao.existsByEmailPersonal(emailPersonal)) {
                throw new EmailInvalidoException("El email personal no es único");
            }
        }
        if (emailCorporativo != null) {
            if (dao.existsByEmailCorporativo(emailCorporativo)) {
                throw new EmailInvalidoException("El email corporativo no es único");
            }
        }
    }

    private boolean existsById(Long id) {
        return dao.existsById(id);
    }

    private void validarContrasenaSave(String contrasena) throws ContrasenaInvalidaException {
        if (contrasena.getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new ContrasenaInvalidaException("La contrasena no puede ser mayor a 72 bytes");
        }
    }

    private void validarEmailsSave(String emailPersonal, String emailCorporativo) throws EmailInvalidoException {
        if (emailCorporativo == null) {
            throw new EmailInvalidoException("El email corporativo es nulo");
        }
        if (dao.existsByEmailPersonal(emailPersonal)) {
            throw new EmailInvalidoException("El email personal no es único");
        }
        if (dao.existsByEmailCorporativo(emailCorporativo)) {
            throw new EmailInvalidoException("El email corporativo no es único");
        }
    }

    private void validarRolIdSave(Long rolId) throws RolInexistenteException {
        if (!rolDAO.existsById(rolId)) {
            throw new RolInexistenteException("El rol no existe");
        }
    }

}
