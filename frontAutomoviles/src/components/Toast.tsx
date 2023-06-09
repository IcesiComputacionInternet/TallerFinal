interface ToastProps {
    title: string;
    message: string;
    onClose: () => void;
}

function Toast ({title, message, onClose}:ToastProps) {
    return (
        <div className="toast show">
            <div className="toast-header">
                <strong className="me-auto">{title}</strong>
                <small>Just now</small>
                <button type="button" className="btn-close" data-bs-dismiss="toast" aria-label="Close" onClick={onClose}></button>
            </div>
            <div className="toast-body dark-texts" style={{color:"#000000"}}>
                {message}
            </div>
        </div>
    )
}

export default Toast;