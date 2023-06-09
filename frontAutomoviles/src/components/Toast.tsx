import { useEffect, useState } from "react";

interface ToastProps {
    title: string;
    message: string;
    onClose: () => void;
}

function Toast ({title, message, onClose}:ToastProps) {
    const [visible, setVisible] = useState(true);

    useEffect(() => {
        const timer = setTimeout(() => {
            setVisible(false);
            onClose();
        }, 3000);
        return () => clearTimeout(timer);
    }, [onClose]);

    const handleClose = () => {
        setVisible(false);
        onClose();
    };

    return (
        <>
            {visible && (
                <div className="toast show">
                    <div className="toast-header">
                        <strong className="me-auto">{title}</strong>
                        <small>Just now</small>
                        <button type="button" className="btn-close" data-bs-dismiss="toast" aria-label="Close" onClick={handleClose}></button>
                    </div>
                    <div className="toast-body dark-texts" style={{color:"#000000"}}>
                        {message}
                    </div>
                </div>
            )}
        </>
    )
}

export default Toast;